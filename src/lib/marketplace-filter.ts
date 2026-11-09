import type { CategorySlug } from "@/data/categories";

export const SORT_KEYS = ["recommended", "price_asc", "price_desc", "rating", "delivery"] as const;
export type SortKey = (typeof SORT_KEYS)[number];

export const PAGE_SIZE = 9;

export interface FilterableService {
  id: string;
  title: string;
  category: CategorySlug;
  priceFromMinor: number;
  deliveryDays: number;
  rating: number;
  reviewCount: number;
  tags: string[];
  /** Extra searchable text, e.g. provider name or category label. */
  searchText?: string;
}

export interface MarketplaceFilters {
  q?: string;
  category?: CategorySlug;
  /** Price bounds in major units (e.g. dollars). */
  minPrice?: number;
  maxPrice?: number;
  maxDelivery?: number;
  minRating?: number;
  sort?: SortKey;
  page?: number;
}

export interface FilterResult<T> {
  items: T[];
  total: number;
  page: number;
  pageCount: number;
}

function tokenize(q: string): string[] {
  return q
    .toLowerCase()
    .normalize("NFKD")
    .replace(/[\u0300-\u036f]/g, "")
    .split(/[^\p{L}\p{N}]+/u)
    .filter(Boolean);
}

function haystack(s: FilterableService): string {
  return [s.title, s.category.replace(/-/g, " "), ...s.tags, s.searchText ?? ""]
    .join(" ")
    .toLowerCase()
    .normalize("NFKD")
    .replace(/[\u0300-\u036f]/g, "");
}

function relevance(s: FilterableService, tokens: string[]): number {
  if (tokens.length === 0) return 0;
  const title = s.title.toLowerCase();
  const tags = s.tags.join(" ").toLowerCase();
  return tokens.reduce((acc, t) => acc + (title.includes(t) ? 3 : 0) + (tags.includes(t) ? 2 : 0), 0);
}

/** Pure search + filter + sort + paginate. Used by the marketplace page and tests. */
export function filterServices<T extends FilterableService>(
  services: T[],
  filters: MarketplaceFilters,
  pageSize: number = PAGE_SIZE,
): FilterResult<T> {
  const tokens = filters.q ? tokenize(filters.q) : [];

  const filtered = services.filter((s) => {
    if (filters.category && s.category !== filters.category) return false;
    const price = s.priceFromMinor / 100;
    if (filters.minPrice !== undefined && price < filters.minPrice) return false;
    if (filters.maxPrice !== undefined && price > filters.maxPrice) return false;
    if (filters.maxDelivery !== undefined && s.deliveryDays > filters.maxDelivery) return false;
    if (filters.minRating !== undefined && s.rating < filters.minRating) return false;
    if (tokens.length) {
      const hay = haystack(s);
      if (!tokens.every((t) => hay.includes(t))) return false;
    }
    return true;
  });

  const sort = filters.sort ?? "recommended";
  const sorted = [...filtered].sort((a, b) => {
    switch (sort) {
      case "price_asc":
        return a.priceFromMinor - b.priceFromMinor;
      case "price_desc":
        return b.priceFromMinor - a.priceFromMinor;
      case "rating":
        return b.rating - a.rating || b.reviewCount - a.reviewCount;
      case "delivery":
        return a.deliveryDays - b.deliveryDays;
      default: {
        const r = relevance(b, tokens) - relevance(a, tokens);
        if (r !== 0) return r;
        const score = (s: FilterableService) => s.rating * Math.log10(s.reviewCount + 10);
        return score(b) - score(a);
      }
    }
  });

  const total = sorted.length;
  const pageCount = Math.max(1, Math.ceil(total / pageSize));
  const page = Math.min(Math.max(1, Math.floor(filters.page ?? 1)), pageCount);
  const start = (page - 1) * pageSize;

  return { items: sorted.slice(start, start + pageSize), total, page, pageCount };
}

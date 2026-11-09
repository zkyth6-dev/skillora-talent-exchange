import { isCategorySlug, type CategorySlug } from "@/data/categories";
import { SORT_KEYS, type SortKey } from "@/lib/marketplace-filter";

/** URL search contract for /services. Every key is optional. */
export interface ServicesSearch {
  q?: string;
  category?: CategorySlug;
  min?: number;
  max?: number;
  delivery?: number;
  rating?: number;
  sort?: SortKey;
  page?: number;
}

function num(v: unknown): number | undefined {
  const n = typeof v === "number" ? v : typeof v === "string" && v.trim() !== "" ? Number(v) : NaN;
  return Number.isFinite(n) && n >= 0 ? n : undefined;
}

/** Sanitises untrusted URL input into a safe, typed search object. */
export function parseServicesSearch(raw: Record<string, unknown>): ServicesSearch {
  const out: ServicesSearch = {};
  if (typeof raw.q === "string" && raw.q.trim()) out.q = raw.q.trim().slice(0, 100);
  if (isCategorySlug(raw.category)) out.category = raw.category;
  const min = num(raw.min);
  if (min !== undefined) out.min = Math.min(min, 100000);
  const max = num(raw.max);
  if (max !== undefined) out.max = Math.min(max, 100000);
  const delivery = num(raw.delivery);
  if (delivery !== undefined && delivery >= 1) out.delivery = Math.min(Math.floor(delivery), 60);
  const rating = num(raw.rating);
  if (rating !== undefined && rating <= 5) out.rating = rating;
  if (typeof raw.sort === "string" && (SORT_KEYS as readonly string[]).includes(raw.sort)) {
    out.sort = raw.sort as SortKey;
  }
  const page = num(raw.page);
  if (page !== undefined && page >= 1) out.page = Math.floor(page);
  return out;
}

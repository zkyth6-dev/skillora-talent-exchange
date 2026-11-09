import { createFileRoute, Link, useNavigate } from "@tanstack/react-router";
import { ChevronLeft, ChevronRight, Search, SearchX, SlidersHorizontal, X } from "lucide-react";
import { useEffect, useMemo, useState, type FormEvent } from "react";
import { FilterPanel, type FilterValues } from "@/components/marketplace/FilterPanel";
import { PreviewBadge } from "@/components/marketplace/PreviewBadge";
import { ServiceCard } from "@/components/marketplace/ServiceCard";
import { Button } from "@/components/ui/button";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Sheet, SheetContent, SheetHeader, SheetTitle, SheetTrigger } from "@/components/ui/sheet";
import { getPreviewProvider, PREVIEW_SERVICES } from "@/data/preview-services";
import { formatNumber } from "@/lib/format";
import { useI18n } from "@/lib/i18n";
import { en } from "@/lib/i18n/messages/en";
import { filterServices, SORT_KEYS, type SortKey } from "@/lib/marketplace-filter";
import { parseServicesSearch, type ServicesSearch } from "@/lib/services-search";

export const Route = createFileRoute("/services/")({
  validateSearch: (raw: Record<string, unknown>): ServicesSearch => parseServicesSearch(raw),
  head: () => ({
    meta: [
      { title: "Explore services — Skillora" },
      {
        name: "description",
        content:
          "Search and filter digital services by category, price, delivery time and rating from freelancers worldwide on Skillora.",
      },
      { property: "og:title", content: "Explore services — Skillora" },
      {
        property: "og:description",
        content: "Find design, development, video, AI, writing and translation services from providers worldwide.",
      },
      { property: "og:type", content: "website" },
      { name: "twitter:card", content: "summary_large_image" },
    ],
  }),
  component: ServicesPage,
});

function ServicesPage() {
  const search = Route.useSearch();
  const navigate = useNavigate({ from: "/services/" });
  const { m, intlLocale } = useI18n();
  const [q, setQ] = useState(search.q ?? "");
  const [sheetOpen, setSheetOpen] = useState(false);

  useEffect(() => setQ(search.q ?? ""), [search.q]);

  const searchable = useMemo(
    () =>
      PREVIEW_SERVICES.map((s) => ({
        ...s,
        searchText: [
          getPreviewProvider(s.providerId)?.name,
          en.categories.labels[s.category],
          m.categories.labels[s.category],
        ].join(" "),
      })),
    [m],
  );

  const result = filterServices(searchable, {
    q: search.q,
    category: search.category,
    minPrice: search.min,
    maxPrice: search.max,
    maxDelivery: search.delivery,
    minRating: search.rating,
    sort: search.sort,
    page: search.page,
  });

  const update = (patch: Partial<ServicesSearch>) =>
    navigate({ search: (prev) => ({ ...prev, ...patch, page: undefined }) });

  const clearAll = () => navigate({ search: {} });

  const onSearch = (e: FormEvent) => {
    e.preventDefault();
    update({ q: q.trim().slice(0, 100) || undefined });
  };

  const filterValues: FilterValues = {
    category: search.category,
    min: search.min,
    max: search.max,
    delivery: search.delivery,
    rating: search.rating,
  };
  const activeCount = Object.values(filterValues).filter((v) => v !== undefined).length;

  return (
    <div className="bg-gradient-soft">
      <div className="container-page py-10 md:py-14">
        <PreviewBadge withNote className="mb-4" />
        <h1 className="text-3xl font-extrabold sm:text-4xl">
          {search.category ? m.categories.labels[search.category] : m.marketplace.title}
        </h1>
        <p className="mt-2 text-muted-foreground">{m.marketplace.subtitle}</p>

        <form
          onSubmit={onSearch}
          role="search"
          className="mt-6 flex max-w-2xl items-center gap-2 rounded-xl border bg-card p-1.5 shadow-card"
        >
          <Search className="ml-2 size-5 shrink-0 text-muted-foreground" aria-hidden="true" />
          <label htmlFor="services-search" className="sr-only">
            {m.marketplace.searchPlaceholder}
          </label>
          <input
            id="services-search"
            type="search"
            value={q}
            maxLength={100}
            onChange={(e) => setQ(e.target.value)}
            placeholder={m.marketplace.searchPlaceholder}
            className="h-10 min-w-0 flex-1 bg-transparent text-sm outline-none placeholder:text-muted-foreground"
          />
          <Button type="submit">{m.hero.searchButton}</Button>
        </form>

        <div className="mt-8 grid gap-8 lg:grid-cols-[16rem_1fr]">
          <aside className="hidden lg:block" aria-label={m.marketplace.filters}>
            <div className="sticky top-24 rounded-2xl border bg-card p-5 shadow-card">
              <h2 className="mb-5 text-sm font-bold">{m.marketplace.filters}</h2>
              <FilterPanel values={filterValues} onChange={update} onClear={clearAll} />
            </div>
          </aside>

          <div>
            <div className="mb-5 flex flex-wrap items-center gap-3">
              <p className="mr-auto text-sm font-medium" aria-live="polite">
                {m.marketplace.results(formatNumber(result.total, intlLocale))}
                {search.q && (
                  <span className="text-muted-foreground">
                    {" "}
                    · “{search.q}”
                    <button
                      type="button"
                      onClick={() => update({ q: undefined })}
                      className="ml-1 inline-flex cursor-pointer align-middle text-muted-foreground hover:text-foreground"
                      aria-label={m.marketplace.clear}
                    >
                      <X className="size-4" />
                    </button>
                  </span>
                )}
              </p>

              <Sheet open={sheetOpen} onOpenChange={setSheetOpen}>
                <SheetTrigger asChild>
                  <Button variant="outline" className="lg:hidden">
                    <SlidersHorizontal aria-hidden="true" />
                    {m.marketplace.filters}
                    {activeCount > 0 && (
                      <span className="rounded-full bg-primary px-1.5 text-[11px] font-bold text-primary-foreground">
                        {activeCount}
                      </span>
                    )}
                  </Button>
                </SheetTrigger>
                <SheetContent side="left" className="w-[88vw] max-w-sm overflow-y-auto">
                  <SheetHeader>
                    <SheetTitle>{m.marketplace.filters}</SheetTitle>
                  </SheetHeader>
                  <div className="px-4 pb-6">
                    <FilterPanel values={filterValues} onChange={update} onClear={clearAll} />
                    <Button className="mt-6 w-full" onClick={() => setSheetOpen(false)}>
                      {m.marketplace.showResults} ({formatNumber(result.total, intlLocale)})
                    </Button>
                  </div>
                </SheetContent>
              </Sheet>

              <Select
                value={search.sort ?? "recommended"}
                onValueChange={(v) =>
                  update({ sort: v === "recommended" ? undefined : (v as SortKey) })
                }
              >
                <SelectTrigger className="w-48 bg-card" aria-label={m.marketplace.sort}>
                  <SelectValue>{m.marketplace.sorts[search.sort ?? "recommended"]}</SelectValue>
                </SelectTrigger>
                <SelectContent align="end">
                  {SORT_KEYS.map((k) => (
                    <SelectItem key={k} value={k}>
                      {m.marketplace.sorts[k]}
                    </SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>

            {result.items.length === 0 ? (
              <div className="flex flex-col items-center rounded-2xl border border-dashed bg-card px-6 py-16 text-center">
                <span className="flex size-14 items-center justify-center rounded-2xl bg-accent text-primary">
                  <SearchX className="size-7" aria-hidden="true" />
                </span>
                <h2 className="mt-5 text-lg font-bold">{m.marketplace.emptyTitle}</h2>
                <p className="mt-1.5 max-w-sm text-sm text-muted-foreground">{m.marketplace.emptyBody}</p>
                <Button variant="outline" className="mt-6" onClick={clearAll}>
                  {m.marketplace.clear}
                </Button>
              </div>
            ) : (
              <div className="grid gap-5 sm:grid-cols-2 xl:grid-cols-3">
                {result.items.map((s, i) => (
                  <ServiceCard key={s.id} service={s} provider={getPreviewProvider(s.providerId)} eager={i < 3} />
                ))}
              </div>
            )}

            {result.pageCount > 1 && (
              <nav className="mt-10 flex items-center justify-center gap-3" aria-label="Pagination">
                <Button variant="outline" size="sm" asChild disabled={result.page <= 1}>
                  <Link
                    from="/services/"
                    search={(prev) => ({ ...prev, page: result.page - 1 > 1 ? result.page - 1 : undefined })}
                    aria-disabled={result.page <= 1}
                    className={result.page <= 1 ? "pointer-events-none opacity-50" : undefined}
                  >
                    <ChevronLeft aria-hidden="true" />
                    {m.marketplace.prev}
                  </Link>
                </Button>
                <span className="text-sm text-muted-foreground">
                  {m.marketplace.pageOf(result.page, result.pageCount)}
                </span>
                <Button variant="outline" size="sm" asChild>
                  <Link
                    from="/services/"
                    search={(prev) => ({ ...prev, page: result.page + 1 })}
                    aria-disabled={result.page >= result.pageCount}
                    className={result.page >= result.pageCount ? "pointer-events-none opacity-50" : undefined}
                  >
                    {m.marketplace.next}
                    <ChevronRight aria-hidden="true" />
                  </Link>
                </Button>
              </nav>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}

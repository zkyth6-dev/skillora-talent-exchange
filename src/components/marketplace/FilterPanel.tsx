import { useEffect, useState, type FormEvent, type ReactNode } from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { CATEGORY_SLUGS, isCategorySlug, type CategorySlug } from "@/data/categories";
import { formatNumber } from "@/lib/format";
import { useI18n } from "@/lib/i18n";

export const DELIVERY_OPTIONS = [1, 3, 7, 14] as const;
export const RATING_OPTIONS = [4, 4.5, 4.8] as const;

export interface FilterValues {
  category?: CategorySlug | undefined;
  min?: number | undefined;
  max?: number | undefined;
  delivery?: number | undefined;
  rating?: number | undefined;
}

export function FilterPanel({
  values,
  onChange,
  onClear,
}: {
  values: FilterValues;
  onChange: (patch: Partial<FilterValues>) => void;
  onClear: () => void;
}) {
  const { m, intlLocale } = useI18n();
  const [min, setMin] = useState(values.min?.toString() ?? "");
  const [max, setMax] = useState(values.max?.toString() ?? "");

  useEffect(() => setMin(values.min?.toString() ?? ""), [values.min]);
  useEffect(() => setMax(values.max?.toString() ?? ""), [values.max]);

  const parse = (v: string) => {
    if (v.trim() === "") return undefined;
    const n = Number(v);
    return Number.isFinite(n) && n >= 0 ? Math.min(n, 100000) : undefined;
  };

  const applyPrice = (e?: FormEvent) => {
    e?.preventDefault();
    let lo = parse(min);
    let hi = parse(max);
    if (lo !== undefined && hi !== undefined && lo > hi) [lo, hi] = [hi, lo];
    onChange({ min: lo, max: hi });
  };

  return (
    <div className="flex flex-col gap-6">
      <div className="grid gap-2">
        <Label htmlFor="filter-category">{m.marketplace.category}</Label>
        <Select
          value={values.category ?? "all"}
          onValueChange={(v) => onChange({ category: isCategorySlug(v) ? v : undefined })}
        >
          <SelectTrigger id="filter-category" className="w-full">
            <SelectValue />
          </SelectTrigger>
          <SelectContent>
            <SelectItem value="all">{m.marketplace.allCategories}</SelectItem>
            {CATEGORY_SLUGS.map((slug) => (
              <SelectItem key={slug} value={slug}>
                {m.categories.labels[slug]}
              </SelectItem>
            ))}
          </SelectContent>
        </Select>
      </div>

      <form className="grid gap-2" onSubmit={applyPrice}>
        <Label>{m.marketplace.price}</Label>
        <div className="flex items-center gap-2">
          <Input
            type="number"
            inputMode="decimal"
            min={0}
            placeholder={m.marketplace.min}
            aria-label={m.marketplace.min}
            value={min}
            onChange={(e) => setMin(e.target.value)}
            onBlur={() => applyPrice()}
          />
          <span className="text-muted-foreground" aria-hidden="true">
            –
          </span>
          <Input
            type="number"
            inputMode="decimal"
            min={0}
            placeholder={m.marketplace.max}
            aria-label={m.marketplace.max}
            value={max}
            onChange={(e) => setMax(e.target.value)}
            onBlur={() => applyPrice()}
          />
        </div>
        <button type="submit" className="sr-only">
          {m.marketplace.apply}
        </button>
      </form>

      <fieldset className="grid gap-2">
        <legend className="mb-2 text-sm font-medium">{m.marketplace.delivery}</legend>
        <div className="flex flex-wrap gap-2">
          <Chip active={values.delivery === undefined} onClick={() => onChange({ delivery: undefined })}>
            {m.marketplace.anyTime}
          </Chip>
          {DELIVERY_OPTIONS.map((d) => (
            <Chip key={d} active={values.delivery === d} onClick={() => onChange({ delivery: d })}>
              {m.marketplace.upTo(d)}
            </Chip>
          ))}
        </div>
      </fieldset>

      <fieldset className="grid gap-2">
        <legend className="mb-2 text-sm font-medium">{m.marketplace.rating}</legend>
        <div className="flex flex-wrap gap-2">
          <Chip active={values.rating === undefined} onClick={() => onChange({ rating: undefined })}>
            {m.marketplace.anyRating}
          </Chip>
          {RATING_OPTIONS.map((r) => (
            <Chip key={r} active={values.rating === r} onClick={() => onChange({ rating: r })}>
              ★ {m.marketplace.ratingAtLeast(formatNumber(r, intlLocale, { minimumFractionDigits: 1 }))}
            </Chip>
          ))}
        </div>
      </fieldset>

      <Button variant="ghost" className="justify-start px-0 text-primary hover:bg-transparent" onClick={onClear}>
        {m.marketplace.clear}
      </Button>
    </div>
  );
}

function Chip({
  active,
  onClick,
  children,
}: {
  active: boolean;
  onClick: () => void;
  children: ReactNode;
}) {
  return (
    <button
      type="button"
      aria-pressed={active}
      onClick={onClick}
      className={
        active
          ? "cursor-pointer rounded-full border border-primary bg-primary px-3 py-1.5 text-xs font-semibold text-primary-foreground"
          : "cursor-pointer rounded-full border bg-card px-3 py-1.5 text-xs font-medium text-foreground transition-colors hover:border-primary/50"
      }
    >
      {children}
    </button>
  );
}

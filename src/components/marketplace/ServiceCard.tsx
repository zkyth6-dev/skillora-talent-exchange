import { BadgeCheck, Clock, Star } from "lucide-react";
import type { PreviewProvider, PreviewService } from "@/data/preview-services";
import { useI18n } from "@/lib/i18n";
import { formatMoney, formatNumber } from "@/lib/format";
import { ProviderAvatar } from "./ProviderAvatar";

export function ServiceCard({
  service,
  provider,
  eager = false,
}: {
  service: PreviewService;
  provider?: PreviewProvider;
  eager?: boolean;
}) {
  const { m, intlLocale } = useI18n();
  return (
    <article className="group flex h-full flex-col overflow-hidden rounded-2xl border bg-card text-card-foreground shadow-card transition-shadow duration-300 hover:shadow-elevated">
      <div className="relative aspect-[4/3] overflow-hidden bg-muted">
        <img
          src={service.cover}
          alt=""
          width={1024}
          height={768}
          loading={eager ? "eager" : "lazy"}
          decoding="async"
          className="size-full object-cover transition-transform duration-500 group-hover:scale-[1.03]"
        />
        <span className="absolute left-3 top-3 rounded-full bg-hero/80 px-2.5 py-1 text-[11px] font-semibold text-hero-foreground backdrop-blur">
          {m.categories.labels[service.category]}
        </span>
      </div>
      <div className="flex flex-1 flex-col gap-3 p-4">
        {provider && (
          <div className="flex items-center gap-2.5">
            <ProviderAvatar name={provider.name} className="size-7 text-[10px]" />
            <span className="truncate text-sm font-semibold">{provider.name}</span>
            {provider.verified && (
              <BadgeCheck className="size-4 shrink-0 text-primary" aria-label={m.service.verified} />
            )}
          </div>
        )}
        <h3 className="line-clamp-2 text-[15px] font-semibold leading-snug">{service.title}</h3>
        <div className="flex items-center gap-3 text-sm text-muted-foreground">
          <span className="inline-flex items-center gap-1">
            <Star className="size-4 fill-warning text-warning" aria-hidden="true" />
            <span className="font-semibold text-foreground">
              {formatNumber(service.rating, intlLocale, { minimumFractionDigits: 1, maximumFractionDigits: 1 })}
            </span>
            <span className="sr-only">/5,</span>
            <span>({m.service.reviews(formatNumber(service.reviewCount, intlLocale))})</span>
          </span>
        </div>
        <div className="mt-auto flex items-end justify-between border-t pt-3">
          <span className="inline-flex items-center gap-1.5 text-xs text-muted-foreground">
            <Clock className="size-3.5" aria-hidden="true" />
            {m.service.delivery(service.deliveryDays)}
          </span>
          <span className="text-right">
            <span className="block text-[11px] uppercase tracking-wide text-muted-foreground">
              {m.service.from}
            </span>
            <span className="text-base font-bold">
              {formatMoney(service.priceFromMinor, service.currency, intlLocale)}
            </span>
          </span>
        </div>
      </div>
    </article>
  );
}

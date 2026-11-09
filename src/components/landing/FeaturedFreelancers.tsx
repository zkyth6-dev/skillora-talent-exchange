import { BadgeCheck, MapPin } from "lucide-react";
import { PreviewBadge } from "@/components/marketplace/PreviewBadge";
import { ProviderAvatar } from "@/components/marketplace/ProviderAvatar";
import { PREVIEW_PROVIDERS, PREVIEW_SERVICES } from "@/data/preview-services";
import { countryName, languageName } from "@/lib/format";
import { useI18n } from "@/lib/i18n";
import { SectionHeading } from "./SectionHeading";

export function FeaturedFreelancers() {
  const { m, intlLocale } = useI18n();
  const providers = PREVIEW_PROVIDERS.slice(0, 6);

  return (
    <section className="container-page py-20 md:py-24" aria-labelledby="freelancers-title">
      <PreviewBadge className="mb-4" />
      <SectionHeading title={m.freelancers.title} subtitle={m.freelancers.subtitle} />
      <ul className="mt-10 grid gap-4 sm:grid-cols-2 lg:grid-cols-3">
        {providers.map((p) => {
          const serviceCount = PREVIEW_SERVICES.filter((s) => s.providerId === p.id).length;
          const cats = Array.from(
            new Set(PREVIEW_SERVICES.filter((s) => s.providerId === p.id).map((s) => s.category)),
          );
          return (
            <li key={p.id} className="flex gap-4 rounded-2xl border bg-card p-5 shadow-card">
              <ProviderAvatar name={p.name} className="size-14 text-base" />
              <div className="min-w-0 flex-1">
                <div className="flex items-center gap-1.5">
                  <h3 className="truncate font-semibold">{p.name}</h3>
                  {p.verified && (
                    <BadgeCheck className="size-4 shrink-0 text-primary" aria-label={m.service.verified} />
                  )}
                </div>
                <p className="text-sm text-muted-foreground">{p.headline}</p>
                <p className="mt-2 flex items-center gap-1.5 text-xs text-muted-foreground">
                  <MapPin className="size-3.5" aria-hidden="true" />
                  {countryName(p.country, intlLocale)}
                  <span aria-hidden="true">·</span>
                  {m.freelancers.speaks}{" "}
                  {p.languages.map((l) => languageName(l, intlLocale)).join(", ")}
                </p>
                <div className="mt-3 flex flex-wrap gap-1.5">
                  {cats.map((c) => (
                    <span key={c} className="rounded-full bg-secondary px-2.5 py-0.5 text-xs font-medium text-secondary-foreground">
                      {m.categories.labels[c]}
                    </span>
                  ))}
                  <span className="rounded-full border px-2.5 py-0.5 text-xs text-muted-foreground">
                    {m.freelancers.services(serviceCount)}
                  </span>
                </div>
              </div>
            </li>
          );
        })}
      </ul>
    </section>
  );
}

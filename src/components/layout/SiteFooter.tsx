import { Link } from "@tanstack/react-router";
import { Logo } from "@/components/brand/Logo";
import type { CategorySlug } from "@/data/categories";
import { LOCALE_LABELS, useI18n } from "@/lib/i18n";
import { SUPPORTED_LOCALES } from "@/lib/platform-config";
import { cn } from "@/lib/utils";

const FOOTER_CATEGORIES: CategorySlug[] = [
  "logo-branding",
  "web-development",
  "video-editing",
  "ai-services",
  "translation",
  "content-writing",
];

export function SiteFooter() {
  const { m, locale, setLocale } = useI18n();
  const linkCls = "text-sm text-hero-muted transition-colors hover:text-hero-foreground";

  return (
    <footer className="bg-hero text-hero-foreground">
      <div className="container-page grid gap-10 py-14 md:grid-cols-[1.4fr_1fr_1fr_1fr]">
        <div className="max-w-xs">
          <Logo tone="hero" />
          <p className="mt-4 text-sm text-hero-muted">{m.footer.tagline}</p>
          <p className="mt-6 text-xs text-hero-muted">{m.footer.currencyNote}</p>
        </div>

        <div>
          <h2 className="text-sm font-semibold">{m.footer.marketplace}</h2>
          <ul className="mt-4 grid gap-2.5">
            <li>
              <Link to="/services" className={linkCls}>
                {m.footer.allServices}
              </Link>
            </li>
            <li>
              <Link to="/services" search={{ sort: "rating" }} className={linkCls}>
                {m.footer.topRated}
              </Link>
            </li>
            <li>
              <Link to="/services" search={{ delivery: 3 }} className={linkCls}>
                {m.footer.fastDelivery}
              </Link>
            </li>
          </ul>
        </div>

        <div>
          <h2 className="text-sm font-semibold">{m.footer.popularCategories}</h2>
          <ul className="mt-4 grid gap-2.5">
            {FOOTER_CATEGORIES.map((slug) => (
              <li key={slug}>
                <Link to="/services" search={{ category: slug }} className={linkCls}>
                  {m.categories.labels[slug]}
                </Link>
              </li>
            ))}
          </ul>
        </div>

        <div>
          <h2 className="text-sm font-semibold">{m.footer.language}</h2>
          <ul className="mt-4 grid gap-2.5">
            {SUPPORTED_LOCALES.map((l) => (
              <li key={l}>
                <button
                  type="button"
                  lang={l}
                  onClick={() => setLocale(l)}
                  aria-pressed={l === locale}
                  className={cn(linkCls, "cursor-pointer", l === locale && "font-semibold text-hero-foreground")}
                >
                  {LOCALE_LABELS[l].native}
                </button>
              </li>
            ))}
          </ul>
        </div>
      </div>
      <div className="border-t border-hero-border">
        <div className="container-page flex flex-col gap-2 py-6 text-xs text-hero-muted sm:flex-row sm:items-center sm:justify-between">
          <span>{m.footer.rights(new Date().getFullYear())}</span>
          <span>{m.meta.tagline}</span>
        </div>
      </div>
    </footer>
  );
}

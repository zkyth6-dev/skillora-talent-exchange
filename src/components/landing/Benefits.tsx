import { Award, Globe2, Receipt, Repeat2 } from "lucide-react";
import { calculateOrderFees } from "@/lib/fees";
import { formatMoney, formatPercent } from "@/lib/format";
import { useI18n } from "@/lib/i18n";
import { PLATFORM_DEFAULTS } from "@/lib/platform-config";
import { SectionHeading } from "./SectionHeading";

const ICONS = [Receipt, Globe2, Repeat2, Award];
const EXAMPLE_ORDER_MINOR = 10000; // $100.00 illustration

export function Benefits() {
  const { m, intlLocale } = useI18n();
  const fees = calculateOrderFees(EXAMPLE_ORDER_MINOR, PLATFORM_DEFAULTS.commissionRate);
  const money = (v: number) => formatMoney(v, PLATFORM_DEFAULTS.baseCurrency, intlLocale);

  return (
    <section className="container-page py-20 md:py-24" aria-labelledby="benefits-title">
      <div className="grid gap-12 lg:grid-cols-[1.3fr_1fr] lg:items-start">
        <div>
          <SectionHeading title={m.benefits.title} subtitle={m.benefits.subtitle} />
          <ul className="mt-10 grid gap-4 sm:grid-cols-2">
            {m.benefits.items.map((item, i) => {
              const Icon = ICONS[i] ?? Receipt;
              return (
                <li key={item.title} className="rounded-2xl border bg-card p-5 shadow-card">
                  <span className="flex size-10 items-center justify-center rounded-xl bg-accent text-primary">
                    <Icon className="size-5" aria-hidden="true" />
                  </span>
                  <h3 className="mt-4 font-bold">{item.title}</h3>
                  <p className="mt-1.5 text-sm text-muted-foreground">{item.body}</p>
                </li>
              );
            })}
          </ul>
        </div>

        <aside className="relative overflow-hidden rounded-3xl bg-gradient-hero p-7 text-hero-foreground shadow-elevated md:p-9">
          <div className="bg-grid-hero pointer-events-none absolute inset-0" aria-hidden="true" />
          <div className="relative">
            <h3 className="text-2xl font-bold">{m.benefits.feeTitle}</h3>
            <p className="mt-2 text-sm text-hero-muted">{m.benefits.feeBody}</p>
            <p className="mt-8 text-xs font-semibold uppercase tracking-wider text-hero-muted">
              {m.benefits.feeExample(money(fees.subtotalMinor))}
            </p>
            <dl className="mt-3 divide-y divide-hero-border rounded-2xl border border-hero-border bg-hero-foreground/5">
              <div className="flex items-center justify-between px-5 py-4">
                <dt className="text-sm text-hero-muted">{m.benefits.feeOrder}</dt>
                <dd className="font-semibold">{money(fees.subtotalMinor)}</dd>
              </div>
              <div className="flex items-center justify-between px-5 py-4">
                <dt className="text-sm text-hero-muted">
                  {m.benefits.feeCommission(formatPercent(fees.rate, intlLocale))}
                </dt>
                <dd className="font-semibold">− {money(fees.commissionMinor)}</dd>
              </div>
              <div className="flex items-center justify-between px-5 py-4">
                <dt className="text-sm font-semibold">{m.benefits.feeNet}</dt>
                <dd className="text-gradient-brand text-2xl font-extrabold">{money(fees.providerNetMinor)}</dd>
              </div>
            </dl>
          </div>
        </aside>
      </div>
    </section>
  );
}

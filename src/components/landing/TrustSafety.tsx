import { Flag, Gavel, ListChecks, LockKeyhole, MessagesSquare, ShieldCheck } from "lucide-react";
import { useI18n } from "@/lib/i18n";

const ICONS = [ShieldCheck, MessagesSquare, ListChecks, Gavel, Flag, LockKeyhole];

export function TrustSafety() {
  const { m } = useI18n();
  return (
    <section className="relative isolate overflow-hidden bg-gradient-hero py-20 text-hero-foreground md:py-24">
      <div className="bg-grid-hero pointer-events-none absolute inset-0 -z-10" aria-hidden="true" />
      <div className="container-page">
        <div className="max-w-2xl">
          <h2 className="text-2xl font-bold sm:text-3xl md:text-4xl">{m.trust.title}</h2>
          <p className="mt-3 text-base text-hero-muted md:text-lg">{m.trust.subtitle}</p>
        </div>
        <ul className="mt-12 grid gap-px overflow-hidden rounded-3xl border border-hero-border bg-hero-border sm:grid-cols-2 lg:grid-cols-3">
          {m.trust.items.map((item, i) => {
            const Icon = ICONS[i] ?? ShieldCheck;
            return (
              <li key={item.title} className="bg-hero p-6 md:p-7">
                <Icon className="size-6 text-highlight" aria-hidden="true" />
                <h3 className="mt-4 font-bold">{item.title}</h3>
                <p className="mt-1.5 text-sm text-hero-muted">{item.body}</p>
              </li>
            );
          })}
        </ul>
        <p className="mt-6 text-xs text-hero-muted">{m.trust.rollout}</p>
      </div>
    </section>
  );
}

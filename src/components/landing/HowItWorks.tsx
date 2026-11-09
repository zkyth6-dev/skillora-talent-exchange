import { useState } from "react";
import { useI18n } from "@/lib/i18n";
import { cn } from "@/lib/utils";
import { SectionHeading } from "./SectionHeading";

export function HowItWorks() {
  const { m } = useI18n();
  const [side, setSide] = useState<"clients" | "providers">("clients");
  const steps = side === "clients" ? m.how.clientSteps : m.how.providerSteps;

  return (
    <section id="how-it-works" className="border-y bg-card py-20 md:py-24" aria-labelledby="how-title">
      <div className="container-page">
        <SectionHeading title={m.how.title} subtitle={m.how.subtitle} align="center" />
        <div
          role="tablist"
          aria-label={m.how.title}
          className="mx-auto mt-8 flex w-fit rounded-full border bg-muted p-1"
        >
          {(["clients", "providers"] as const).map((key) => (
            <button
              key={key}
              type="button"
              role="tab"
              aria-selected={side === key}
              onClick={() => setSide(key)}
              className={cn(
                "cursor-pointer rounded-full px-5 py-2 text-sm font-semibold transition-all",
                side === key ? "bg-card text-foreground shadow-card" : "text-muted-foreground hover:text-foreground",
              )}
            >
              {m.how[key]}
            </button>
          ))}
        </div>

        <ol key={side} className="mt-12 grid gap-6 md:grid-cols-3">
          {steps.map((step, i) => (
            <li
              key={step.title}
              className="animate-rise relative rounded-2xl border bg-background p-6"
              style={{ animationDelay: `${i * 90}ms` }}
            >
              <span className="text-gradient-brand text-5xl font-extrabold leading-none">
                {String(i + 1).padStart(2, "0")}
              </span>
              <h3 className="mt-5 text-lg font-bold">{step.title}</h3>
              <p className="mt-2 text-sm text-muted-foreground">{step.body}</p>
            </li>
          ))}
        </ol>
      </div>
    </section>
  );
}

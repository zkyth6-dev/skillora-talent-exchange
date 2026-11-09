import { Link, useNavigate } from "@tanstack/react-router";
import { ArrowRight, CheckCircle2, Percent, Search, UserRoundCog } from "lucide-react";
import { useState, type FormEvent } from "react";
import heroImage from "@/assets/hero-freelancer.jpg";
import { Button } from "@/components/ui/button";
import { useI18n } from "@/lib/i18n";

export function Hero() {
  const { m } = useI18n();
  const navigate = useNavigate();
  const [q, setQ] = useState("");

  const onSubmit = (e: FormEvent) => {
    e.preventDefault();
    const term = q.trim().slice(0, 100);
    navigate({ to: "/services", search: term ? { q: term } : {} });
  };

  return (
    <section className="relative isolate overflow-hidden bg-gradient-hero text-hero-foreground">
      <div className="bg-grid-hero pointer-events-none absolute inset-0 -z-10" aria-hidden="true" />
      <div className="container-page grid items-center gap-12 py-16 md:py-20 lg:grid-cols-[1.1fr_1fr] lg:gap-16 lg:py-28">
        <div className="animate-rise">
          <span className="inline-flex items-center gap-2 rounded-full border border-hero-border bg-hero-foreground/5 px-3 py-1.5 text-xs font-medium text-hero-muted">
            <span className="size-1.5 rounded-full bg-highlight" aria-hidden="true" />
            {m.hero.eyebrow}
          </span>
          <h1 className="mt-6 text-4xl font-extrabold leading-[1.05] sm:text-5xl lg:text-6xl xl:text-7xl">
            {m.hero.titleA}
            <br />
            <span className="text-gradient-brand">{m.hero.titleB}</span>
          </h1>
          <p className="mt-6 max-w-xl text-base text-hero-muted sm:text-lg">{m.hero.subtitle}</p>

          <form
            onSubmit={onSubmit}
            role="search"
            className="mt-8 flex max-w-xl items-center gap-2 rounded-2xl border border-hero-border bg-card p-2 shadow-elevated"
          >
            <label htmlFor="hero-search" className="sr-only">
              {m.hero.searchLabel}
            </label>
            <Search className="ml-2 size-5 shrink-0 text-muted-foreground" aria-hidden="true" />
            <input
              id="hero-search"
              type="search"
              value={q}
              maxLength={100}
              onChange={(e) => setQ(e.target.value)}
              placeholder={m.hero.searchPlaceholder}
              className="h-11 min-w-0 flex-1 bg-transparent text-sm text-card-foreground outline-none placeholder:text-muted-foreground sm:text-base"
            />
            <Button type="submit" variant="hero" size="lg" className="shrink-0">
              <span className="hidden sm:inline">{m.hero.searchButton}</span>
              <ArrowRight aria-hidden="true" />
            </Button>
          </form>

          <div className="mt-5 flex flex-wrap items-center gap-2 text-sm">
            <span className="text-hero-muted">{m.hero.popular}:</span>
            {m.hero.popularTerms.map((term) => (
              <Link
                key={term}
                to="/services"
                search={{ q: term }}
                className="rounded-full border border-hero-border px-3 py-1 text-hero-foreground/90 transition-colors hover:bg-hero-foreground/10"
              >
                {term}
              </Link>
            ))}
          </div>
        </div>

        <div className="relative mx-auto w-full max-w-md lg:max-w-none">
          <div className="absolute -inset-6 -z-10 rounded-[2.5rem] bg-gradient-brand opacity-25 blur-3xl" aria-hidden="true" />
          <div className="overflow-hidden rounded-[2rem] border border-hero-border shadow-elevated">
            <img
              src={heroImage}
              alt="A freelancer working on a laptop in a city studio at night"
              width={1280}
              height={1536}
              fetchPriority="high"
              className="aspect-[4/5] w-full object-cover"
            />
          </div>

          <div className="animate-float absolute -left-4 top-8 flex items-center gap-3 rounded-2xl border bg-card p-3 pr-4 text-card-foreground shadow-elevated sm:-left-10">
            <span className="flex size-9 items-center justify-center rounded-xl bg-success/15">
              <CheckCircle2 className="size-5 text-success" aria-hidden="true" />
            </span>
            <span>
              <span className="block text-sm font-semibold">{m.hero.cardDelivered}</span>
              <span className="block text-xs text-muted-foreground">{m.hero.cardDeliveredSub}</span>
            </span>
          </div>

          <div className="absolute -right-3 bottom-24 flex items-center gap-3 rounded-2xl border bg-card p-3 pr-4 text-card-foreground shadow-elevated sm:-right-8 [animation-delay:1.2s] animate-float">
            <span className="flex size-9 items-center justify-center rounded-xl bg-accent">
              <Percent className="size-5 text-primary" aria-hidden="true" />
            </span>
            <span>
              <span className="block text-sm font-semibold">{m.hero.cardFee}</span>
              <span className="block text-xs text-muted-foreground">{m.hero.cardFeeSub}</span>
            </span>
          </div>

          <div className="absolute -bottom-5 left-6 hidden items-center gap-3 rounded-2xl border bg-card p-3 pr-4 text-card-foreground shadow-elevated sm:flex">
            <span className="flex size-9 items-center justify-center rounded-xl bg-gradient-brand">
              <UserRoundCog className="size-5 text-primary-foreground" aria-hidden="true" />
            </span>
            <span>
              <span className="block text-sm font-semibold">{m.hero.cardRoles}</span>
              <span className="block text-xs text-muted-foreground">{m.hero.cardRolesSub}</span>
            </span>
          </div>
        </div>
      </div>
    </section>
  );
}

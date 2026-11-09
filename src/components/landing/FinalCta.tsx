import { Link } from "@tanstack/react-router";
import { ArrowRight } from "lucide-react";
import { LogoMark } from "@/components/brand/Logo";
import { Button } from "@/components/ui/button";
import { useI18n } from "@/lib/i18n";

export function FinalCta() {
  const { m } = useI18n();
  return (
    <section className="container-page py-20 md:py-24">
      <div className="relative overflow-hidden rounded-[2rem] border bg-card p-8 text-center shadow-elevated md:p-16">
        <div className="pointer-events-none absolute -top-24 left-1/2 size-72 -translate-x-1/2 rounded-full bg-gradient-brand opacity-20 blur-3xl" aria-hidden="true" />
        <LogoMark className="relative mx-auto size-12" />
        <h2 className="relative mx-auto mt-6 max-w-2xl text-3xl font-extrabold sm:text-4xl md:text-5xl">
          {m.cta.title}
        </h2>
        <p className="relative mx-auto mt-4 max-w-xl text-muted-foreground md:text-lg">{m.cta.body}</p>
        <Button variant="hero" size="xl" className="relative mt-8" asChild>
          <Link to="/services">
            {m.cta.button}
            <ArrowRight aria-hidden="true" />
          </Link>
        </Button>
      </div>
    </section>
  );
}

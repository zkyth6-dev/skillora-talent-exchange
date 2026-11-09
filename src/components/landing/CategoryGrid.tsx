import { Link } from "@tanstack/react-router";
import { ArrowRight } from "lucide-react";
import { Button } from "@/components/ui/button";
import { CATEGORY_ICONS, CATEGORY_SLUGS } from "@/data/categories";
import { useI18n } from "@/lib/i18n";
import { SectionHeading } from "./SectionHeading";

export function CategoryGrid() {
  const { m } = useI18n();
  return (
    <section className="container-page py-20 md:py-24" aria-labelledby="categories-title">
      <SectionHeading
        title={m.categories.title}
        subtitle={m.categories.subtitle}
        action={
          <Button variant="outline" asChild>
            <Link to="/services">
              {m.categories.viewAll}
              <ArrowRight aria-hidden="true" />
            </Link>
          </Button>
        }
      />
      <ul className="mt-10 grid grid-cols-2 gap-3 sm:grid-cols-3 lg:grid-cols-4 xl:grid-cols-6">
        {CATEGORY_SLUGS.map((slug) => {
          const Icon = CATEGORY_ICONS[slug];
          return (
            <li key={slug}>
              <Link
                to="/services"
                search={{ category: slug }}
                className="group flex h-full flex-col gap-3 rounded-2xl border bg-card p-4 shadow-card transition-all duration-200 hover:-translate-y-0.5 hover:border-primary/40 hover:shadow-elevated"
              >
                <span className="flex size-10 items-center justify-center rounded-xl bg-accent text-primary transition-colors group-hover:bg-gradient-brand group-hover:text-primary-foreground">
                  <Icon className="size-5" aria-hidden="true" />
                </span>
                <span className="text-sm font-semibold leading-snug">{m.categories.labels[slug]}</span>
              </Link>
            </li>
          );
        })}
      </ul>
    </section>
  );
}

import { Link } from "@tanstack/react-router";
import { ArrowRight } from "lucide-react";
import { PreviewBadge } from "@/components/marketplace/PreviewBadge";
import { ServiceCard } from "@/components/marketplace/ServiceCard";
import { Button } from "@/components/ui/button";
import { getPreviewProvider, PREVIEW_SERVICES } from "@/data/preview-services";
import { useI18n } from "@/lib/i18n";
import { SectionHeading } from "./SectionHeading";

const FEATURED_IDS = ["s1", "s3", "s5", "s2"];

export function FeaturedServices() {
  const { m } = useI18n();
  const items = FEATURED_IDS.map((id) => PREVIEW_SERVICES.find((s) => s.id === id)!).filter(Boolean);

  return (
    <section className="bg-gradient-soft py-20 md:py-24">
      <div className="container-page">
        <PreviewBadge withNote className="mb-4" />
        <SectionHeading
          title={m.featured.title}
          subtitle={m.featured.subtitle}
          action={
            <Button variant="outline" asChild>
              <Link to="/services">
                {m.featured.viewAll}
                <ArrowRight aria-hidden="true" />
              </Link>
            </Button>
          }
        />
        <div className="mt-10 grid gap-5 sm:grid-cols-2 lg:grid-cols-4">
          {items.map((s) => (
            <ServiceCard key={s.id} service={s} provider={getPreviewProvider(s.providerId)} />
          ))}
        </div>
      </div>
    </section>
  );
}

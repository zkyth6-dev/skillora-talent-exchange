import { createFileRoute } from "@tanstack/react-router";
import { Benefits } from "@/components/landing/Benefits";
import { CategoryGrid } from "@/components/landing/CategoryGrid";
import { FeaturedFreelancers } from "@/components/landing/FeaturedFreelancers";
import { FeaturedServices } from "@/components/landing/FeaturedServices";
import { FinalCta } from "@/components/landing/FinalCta";
import { Hero } from "@/components/landing/Hero";
import { HowItWorks } from "@/components/landing/HowItWorks";
import { TrustSafety } from "@/components/landing/TrustSafety";

export const Route = createFileRoute("/")({
  head: () => ({
    meta: [
      { title: "Skillora — Your Skills. Your Opportunity." },
      {
        name: "description",
        content:
          "Skillora is a global marketplace for digital services. Hire trusted freelancers or turn your skills into real work — with transparent fees.",
      },
      { property: "og:title", content: "Skillora — Your Skills. Your Opportunity." },
      {
        property: "og:description",
        content:
          "A global digital services marketplace connecting people who have skills with people and businesses who need them.",
      },
      { property: "og:type", content: "website" },
      { name: "twitter:card", content: "summary_large_image" },
    ],
  }),
  component: Index,
});

function Index() {
  return (
    <>
      <Hero />
      <CategoryGrid />
      <FeaturedServices />
      <HowItWorks />
      <FeaturedFreelancers />
      <Benefits />
      <TrustSafety />
      <FinalCta />
    </>
  );
}

/**
 * PREVIEW DATA — development only.
 *
 * These listings exist so the marketplace UI can be built and tested before
 * real providers publish services. Every surface that renders them must show
 * the "Preview data" badge. Replace with database reads in Phase 2.
 */
import coverBranding from "@/assets/cover-branding.jpg";
import coverWebdev from "@/assets/cover-webdev.jpg";
import coverVideo from "@/assets/cover-video.jpg";
import coverUiux from "@/assets/cover-uiux.jpg";
import coverAi from "@/assets/cover-ai.jpg";
import coverWriting from "@/assets/cover-writing.jpg";
import type { CategorySlug } from "./categories";

export const IS_PREVIEW_DATA = true;

export interface PreviewProvider {
  id: string;
  name: string;
  headline: string;
  country: string; // ISO 3166-1 alpha-2
  languages: string[]; // ISO 639-1
  verified: boolean;
}

export interface PreviewService {
  id: string;
  title: string;
  category: CategorySlug;
  providerId: string;
  cover: string;
  priceFromMinor: number; // USD cents
  currency: "USD";
  deliveryDays: number;
  rating: number;
  reviewCount: number;
  tags: string[];
}

export const PREVIEW_PROVIDERS: PreviewProvider[] = [
  { id: "p1", name: "Amara Okafor", headline: "Brand identity designer", country: "NG", languages: ["en", "fr"], verified: true },
  { id: "p2", name: "Rizky Pratama", headline: "Full-stack web developer", country: "ID", languages: ["id", "en"], verified: true },
  { id: "p3", name: "Lucía Fernández", headline: "Video editor & colorist", country: "AR", languages: ["es", "en"], verified: true },
  { id: "p4", name: "Kenji Watanabe", headline: "Product & UI/UX designer", country: "JP", languages: ["ja", "en"], verified: false },
  { id: "p5", name: "Priya Raman", headline: "AI & automation engineer", country: "IN", languages: ["en", "hi", "ta"], verified: true },
  { id: "p6", name: "Jonas Becker", headline: "Translator DE · EN · NL", country: "DE", languages: ["de", "en", "nl"], verified: true },
  { id: "p7", name: "Siti Nurhaliza", headline: "Social media strategist", country: "MY", languages: ["ms", "en", "id"], verified: false },
  { id: "p8", name: "Mateo Rossi", headline: "Copywriter & SEO writer", country: "IT", languages: ["it", "en"], verified: true },
];

export const PREVIEW_SERVICES: PreviewService[] = [
  { id: "s1", title: "Complete brand identity with logo, palette and guidelines", category: "logo-branding", providerId: "p1", cover: coverBranding, priceFromMinor: 18000, currency: "USD", deliveryDays: 5, rating: 4.9, reviewCount: 214, tags: ["logo", "branding", "brand guidelines", "identity"] },
  { id: "s2", title: "Responsive business website built with React", category: "web-development", providerId: "p2", cover: coverWebdev, priceFromMinor: 35000, currency: "USD", deliveryDays: 10, rating: 4.8, reviewCount: 132, tags: ["website", "react", "landing page", "frontend"] },
  { id: "s3", title: "Cinematic YouTube and reel video editing", category: "video-editing", providerId: "p3", cover: coverVideo, priceFromMinor: 6000, currency: "USD", deliveryDays: 3, rating: 4.9, reviewCount: 388, tags: ["youtube", "reels", "color grading", "video"] },
  { id: "s4", title: "Mobile app UI/UX design in Figma", category: "ui-ux", providerId: "p4", cover: coverUiux, priceFromMinor: 25000, currency: "USD", deliveryDays: 7, rating: 4.7, reviewCount: 76, tags: ["figma", "app design", "wireframes", "prototype"] },
  { id: "s5", title: "Custom AI chatbot trained on your documents", category: "ai-services", providerId: "p5", cover: coverAi, priceFromMinor: 40000, currency: "USD", deliveryDays: 7, rating: 4.8, reviewCount: 58, tags: ["ai", "chatbot", "automation", "llm"] },
  { id: "s6", title: "Professional German ⇄ English translation", category: "translation", providerId: "p6", cover: coverWriting, priceFromMinor: 3000, currency: "USD", deliveryDays: 2, rating: 5.0, reviewCount: 241, tags: ["german", "english", "translation", "localization"] },
  { id: "s7", title: "Monthly Instagram & TikTok content plan", category: "social-media", providerId: "p7", cover: coverUiux, priceFromMinor: 15000, currency: "USD", deliveryDays: 5, rating: 4.6, reviewCount: 41, tags: ["instagram", "tiktok", "content calendar", "social"] },
  { id: "s8", title: "SEO blog articles that rank and convert", category: "content-writing", providerId: "p8", cover: coverWriting, priceFromMinor: 5000, currency: "USD", deliveryDays: 3, rating: 4.8, reviewCount: 167, tags: ["blog", "seo", "copywriting", "articles"] },
  { id: "s9", title: "Minimal logo design with unlimited concepts", category: "logo-branding", providerId: "p1", cover: coverBranding, priceFromMinor: 7500, currency: "USD", deliveryDays: 2, rating: 4.9, reviewCount: 97, tags: ["logo", "minimal", "branding"] },
  { id: "s10", title: "Bug fixing and performance tuning for web apps", category: "programming", providerId: "p2", cover: coverWebdev, priceFromMinor: 4500, currency: "USD", deliveryDays: 1, rating: 4.7, reviewCount: 203, tags: ["bug fix", "javascript", "performance", "typescript"] },
  { id: "s11", title: "Technical SEO audit with action plan", category: "seo", providerId: "p8", cover: coverAi, priceFromMinor: 12000, currency: "USD", deliveryDays: 4, rating: 4.6, reviewCount: 39, tags: ["seo audit", "technical seo", "google"] },
  { id: "s12", title: "Short-form promo video for product launches", category: "video-editing", providerId: "p3", cover: coverVideo, priceFromMinor: 9000, currency: "USD", deliveryDays: 4, rating: 4.8, reviewCount: 122, tags: ["promo", "motion", "ads", "video"] },
  { id: "s13", title: "Workflow automation with AI agents", category: "ai-services", providerId: "p5", cover: coverAi, priceFromMinor: 22000, currency: "USD", deliveryDays: 6, rating: 4.9, reviewCount: 64, tags: ["automation", "ai agents", "zapier", "integration"] },
  { id: "s14", title: "Website localization into Dutch and German", category: "translation", providerId: "p6", cover: coverWriting, priceFromMinor: 8000, currency: "USD", deliveryDays: 5, rating: 4.9, reviewCount: 58, tags: ["dutch", "german", "localization", "website"] },
];

export function getPreviewProvider(id: string): PreviewProvider | undefined {
  return PREVIEW_PROVIDERS.find((p) => p.id === id);
}

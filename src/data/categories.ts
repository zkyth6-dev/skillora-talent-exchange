import {
  Brush,
  Briefcase,
  Camera,
  Clapperboard,
  FileText,
  Film,
  Globe,
  GraduationCap,
  Headset,
  Languages,
  LayoutTemplate,
  Music,
  Palette,
  PenTool,
  Puzzle,
  Search,
  Share2,
  Smartphone,
  Sparkles,
  Table,
  Terminal,
  TrendingUp,
  type LucideIcon,
} from "lucide-react";

/**
 * Seed category catalogue. Slugs are stable identifiers (also used in URLs and,
 * later, as `categories.slug` in the database). Labels live in i18n messages.
 * Admins will manage the live list once the admin panel ships.
 */
export const CATEGORY_SLUGS = [
  "graphic-design",
  "logo-branding",
  "video-editing",
  "photography",
  "illustration",
  "web-development",
  "mobile-apps",
  "ui-ux",
  "programming",
  "ai-services",
  "content-writing",
  "translation",
  "data-entry",
  "virtual-assistance",
  "social-media",
  "digital-marketing",
  "seo",
  "audio-production",
  "animation",
  "business-consulting",
  "education",
  "other",
] as const;

export type CategorySlug = (typeof CATEGORY_SLUGS)[number];

export const CATEGORY_ICONS: Record<CategorySlug, LucideIcon> = {
  "graphic-design": Palette,
  "logo-branding": PenTool,
  "video-editing": Clapperboard,
  photography: Camera,
  illustration: Brush,
  "web-development": Globe,
  "mobile-apps": Smartphone,
  "ui-ux": LayoutTemplate,
  programming: Terminal,
  "ai-services": Sparkles,
  "content-writing": FileText,
  translation: Languages,
  "data-entry": Table,
  "virtual-assistance": Headset,
  "social-media": Share2,
  "digital-marketing": TrendingUp,
  seo: Search,
  "audio-production": Music,
  animation: Film,
  "business-consulting": Briefcase,
  education: GraduationCap,
  other: Puzzle,
};

export function isCategorySlug(value: unknown): value is CategorySlug {
  return typeof value === "string" && (CATEGORY_SLUGS as readonly string[]).includes(value);
}

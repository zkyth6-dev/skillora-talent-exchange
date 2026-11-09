/**
 * Platform-wide defaults. These are fallbacks only: once the admin panel exists,
 * values are read from the `platform_settings` table and these act as defaults.
 */
export const PLATFORM_DEFAULTS = {
  /** Commission on each successfully completed order (0.05 = 5%). */
  commissionRate: 0.05,
  /** Base currency for preview listings. Real listings store their own currency. */
  baseCurrency: "USD",
} as const;

export const SUPPORTED_LOCALES = ["en", "id"] as const;
export type Locale = (typeof SUPPORTED_LOCALES)[number];
export const DEFAULT_LOCALE: Locale = "en";

/** BCP-47 tags used for Intl formatting per UI locale. */
export const INTL_LOCALE: Record<Locale, string> = {
  en: "en-US",
  id: "id-ID",
};

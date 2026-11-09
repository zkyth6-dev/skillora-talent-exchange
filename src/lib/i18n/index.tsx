import { createContext, useCallback, useContext, useEffect, useMemo, useState, type ReactNode } from "react";
import { DEFAULT_LOCALE, INTL_LOCALE, SUPPORTED_LOCALES, type Locale } from "@/lib/platform-config";
import { en, type Messages } from "./messages/en";
import { id } from "./messages/id";

const DICTIONARIES: Record<Locale, Messages> = { en, id };
const STORAGE_KEY = "skillora.locale";

export const LOCALE_LABELS: Record<Locale, { short: string; native: string }> = {
  en: { short: "EN", native: "English" },
  id: { short: "ID", native: "Bahasa Indonesia" },
};

interface I18nValue {
  locale: Locale;
  intlLocale: string;
  m: Messages;
  setLocale: (l: Locale) => void;
}

const I18nContext = createContext<I18nValue | null>(null);

function isLocale(v: unknown): v is Locale {
  return typeof v === "string" && (SUPPORTED_LOCALES as readonly string[]).includes(v);
}

export function I18nProvider({ children }: { children: ReactNode }) {
  // SSR always renders the default locale; the stored preference applies after hydration.
  const [locale, setLocaleState] = useState<Locale>(DEFAULT_LOCALE);

  useEffect(() => {
    const stored = window.localStorage.getItem(STORAGE_KEY);
    if (isLocale(stored)) {
      setLocaleState(stored);
      return;
    }
    const browser = window.navigator.language?.slice(0, 2);
    if (isLocale(browser)) setLocaleState(browser);
  }, []);

  useEffect(() => {
    document.documentElement.lang = locale;
  }, [locale]);

  const setLocale = useCallback((l: Locale) => {
    setLocaleState(l);
    window.localStorage.setItem(STORAGE_KEY, l);
  }, []);

  const value = useMemo<I18nValue>(
    () => ({ locale, intlLocale: INTL_LOCALE[locale], m: DICTIONARIES[locale], setLocale }),
    [locale, setLocale],
  );

  return <I18nContext.Provider value={value}>{children}</I18nContext.Provider>;
}

export function useI18n(): I18nValue {
  const ctx = useContext(I18nContext);
  if (!ctx) throw new Error("useI18n must be used inside <I18nProvider>");
  return ctx;
}

/** Locale-aware formatting helpers built on Intl. Never hand-format money or dates. */

const MINOR_UNIT_DIGITS: Record<string, number> = { JPY: 0, KRW: 0, IDR: 0, VND: 0 };

export function minorDigits(currency: string): number {
  return MINOR_UNIT_DIGITS[currency.toUpperCase()] ?? 2;
}

export function formatMoney(amountMinor: number, currency: string, intlLocale: string): string {
  const digits = minorDigits(currency);
  const major = amountMinor / 10 ** digits;
  return new Intl.NumberFormat(intlLocale, {
    style: "currency",
    currency,
    minimumFractionDigits: major % 1 === 0 ? 0 : digits,
    maximumFractionDigits: digits,
  }).format(major);
}

export function formatNumber(value: number, intlLocale: string, opts?: Intl.NumberFormatOptions) {
  return new Intl.NumberFormat(intlLocale, opts).format(value);
}

export function formatPercent(rate: number, intlLocale: string) {
  return new Intl.NumberFormat(intlLocale, { style: "percent", maximumFractionDigits: 1 }).format(
    rate,
  );
}

export function formatDate(
  date: Date | string,
  intlLocale: string,
  opts: Intl.DateTimeFormatOptions = { dateStyle: "medium" },
  timeZone?: string,
) {
  return new Intl.DateTimeFormat(intlLocale, { ...opts, timeZone }).format(new Date(date));
}

export function countryName(isoCode: string, intlLocale: string): string {
  try {
    return new Intl.DisplayNames([intlLocale], { type: "region" }).of(isoCode) ?? isoCode;
  } catch {
    return isoCode;
  }
}

export function languageName(isoCode: string, intlLocale: string): string {
  try {
    return new Intl.DisplayNames([intlLocale], { type: "language" }).of(isoCode) ?? isoCode;
  } catch {
    return isoCode;
  }
}

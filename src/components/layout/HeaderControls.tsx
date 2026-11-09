import { Check, Globe, Moon, Sun } from "lucide-react";
import { Button } from "@/components/ui/button";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import { LOCALE_LABELS, useI18n } from "@/lib/i18n";
import { SUPPORTED_LOCALES } from "@/lib/platform-config";
import { useTheme } from "@/lib/theme";

export function LanguageSwitcher() {
  const { m, locale, setLocale } = useI18n();
  return (
    <DropdownMenu>
      <DropdownMenuTrigger asChild>
        <Button variant="ghost" size="sm" className="gap-1.5" aria-label={m.nav.language}>
          <Globe aria-hidden="true" />
          <span className="font-semibold">{LOCALE_LABELS[locale].short}</span>
        </Button>
      </DropdownMenuTrigger>
      <DropdownMenuContent align="end" className="min-w-44">
        <DropdownMenuLabel>{m.nav.language}</DropdownMenuLabel>
        <DropdownMenuSeparator />
        {SUPPORTED_LOCALES.map((l) => (
          <DropdownMenuItem key={l} onSelect={() => setLocale(l)} className="justify-between">
            <span lang={l}>{LOCALE_LABELS[l].native}</span>
            {l === locale && <Check className="size-4 text-primary" aria-hidden="true" />}
          </DropdownMenuItem>
        ))}
      </DropdownMenuContent>
    </DropdownMenu>
  );
}

export function ThemeToggle() {
  const { m } = useI18n();
  const { isDark, toggle } = useTheme();
  return (
    <Button
      variant="ghost"
      size="icon"
      onClick={toggle}
      aria-label={isDark ? m.nav.themeLight : m.nav.themeDark}
    >
      {isDark ? <Sun aria-hidden="true" /> : <Moon aria-hidden="true" />}
    </Button>
  );
}

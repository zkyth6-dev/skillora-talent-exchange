import { Link } from "@tanstack/react-router";
import { ChevronDown, Menu } from "lucide-react";
import { useState } from "react";
import { Logo } from "@/components/brand/Logo";
import { Button } from "@/components/ui/button";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import { Sheet, SheetContent, SheetHeader, SheetTitle, SheetTrigger } from "@/components/ui/sheet";
import { CATEGORY_ICONS, CATEGORY_SLUGS } from "@/data/categories";
import { useI18n } from "@/lib/i18n";
import { LanguageSwitcher, ThemeToggle } from "./HeaderControls";

export function SiteHeader() {
  const { m } = useI18n();
  const [open, setOpen] = useState(false);

  return (
    <header className="sticky top-0 z-40 border-b bg-background/85 backdrop-blur-md supports-[backdrop-filter]:bg-background/70">
      <div className="container-page flex h-16 items-center gap-6">
        <Link to="/" aria-label={m.nav.home} className="shrink-0">
          <Logo />
        </Link>

        <nav className="hidden items-center gap-1 md:flex" aria-label="Main">
          <Button variant="ghost" size="sm" asChild>
            <Link to="/services" activeProps={{ className: "text-primary" }}>
              {m.nav.explore}
            </Link>
          </Button>
          <DropdownMenu>
            <DropdownMenuTrigger asChild>
              <Button variant="ghost" size="sm" className="gap-1">
                {m.nav.categories}
                <ChevronDown aria-hidden="true" />
              </Button>
            </DropdownMenuTrigger>
            <DropdownMenuContent align="start" className="grid w-[34rem] grid-cols-2 gap-0.5 p-2">
              {CATEGORY_SLUGS.map((slug) => {
                const Icon = CATEGORY_ICONS[slug];
                return (
                  <DropdownMenuItem key={slug} asChild>
                    <Link to="/services" search={{ category: slug }} className="gap-2.5">
                      <Icon className="size-4 text-primary" aria-hidden="true" />
                      {m.categories.labels[slug]}
                    </Link>
                  </DropdownMenuItem>
                );
              })}
            </DropdownMenuContent>
          </DropdownMenu>
        </nav>

        <div className="ml-auto flex items-center gap-1">
          <div className="hidden items-center gap-1 sm:flex">
            <LanguageSwitcher />
            <ThemeToggle />
          </div>
          <Button size="sm" className="hidden md:inline-flex" asChild>
            <Link to="/services">{m.nav.explore}</Link>
          </Button>

          <Sheet open={open} onOpenChange={setOpen}>
            <SheetTrigger asChild>
              <Button variant="ghost" size="icon" className="md:hidden" aria-label={m.nav.menu}>
                <Menu aria-hidden="true" />
              </Button>
            </SheetTrigger>
            <SheetContent side="right" className="w-[86vw] max-w-sm overflow-y-auto">
              <SheetHeader className="text-left">
                <SheetTitle asChild>
                  <span>
                    <Logo />
                  </span>
                </SheetTitle>
              </SheetHeader>
              <div className="flex flex-col gap-6 px-4 pb-8">
                <Button size="lg" asChild onClick={() => setOpen(false)}>
                  <Link to="/services">{m.nav.explore}</Link>
                </Button>
                <div>
                  <p className="mb-2 text-xs font-semibold uppercase tracking-wider text-muted-foreground">
                    {m.nav.categories}
                  </p>
                  <ul className="grid gap-0.5">
                    {CATEGORY_SLUGS.map((slug) => {
                      const Icon = CATEGORY_ICONS[slug];
                      return (
                        <li key={slug}>
                          <Link
                            to="/services"
                            search={{ category: slug }}
                            onClick={() => setOpen(false)}
                            className="flex items-center gap-3 rounded-lg px-2 py-2 text-sm hover:bg-accent"
                          >
                            <Icon className="size-4 text-primary" aria-hidden="true" />
                            {m.categories.labels[slug]}
                          </Link>
                        </li>
                      );
                    })}
                  </ul>
                </div>
                <div className="flex items-center gap-2 border-t pt-4">
                  <LanguageSwitcher />
                  <ThemeToggle />
                </div>
              </div>
            </SheetContent>
          </Sheet>
        </div>
      </div>
    </header>
  );
}

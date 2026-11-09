import { cn } from "@/lib/utils";

export function LogoMark({ className }: { className?: string }) {
  return (
    <svg viewBox="0 0 32 32" aria-hidden="true" className={cn("size-8", className)}>
      <defs>
        <linearGradient id="skillora-g" x1="0" y1="0" x2="32" y2="32" gradientUnits="userSpaceOnUse">
          <stop offset="0" stopColor="var(--electric)" />
          <stop offset="1" stopColor="var(--highlight)" />
        </linearGradient>
      </defs>
      <rect width="32" height="32" rx="9" fill="url(#skillora-g)" />
      <path
        d="M21.5 10.2c-1.3-1.3-3.2-2-5.4-2-3.4 0-5.8 1.8-5.8 4.5 0 2.8 2.4 3.8 5.6 4.4 2.3.4 3.3.9 3.3 1.9 0 1.1-1.2 1.9-3.1 1.9-1.8 0-3.3-.7-4.4-1.9"
        fill="none"
        stroke="white"
        strokeWidth="2.6"
        strokeLinecap="round"
      />
      <circle cx="22.4" cy="22.6" r="1.9" fill="white" />
    </svg>
  );
}

export function Logo({ className, tone = "default" }: { className?: string; tone?: "default" | "hero" }) {
  return (
    <span className={cn("inline-flex items-center gap-2.5", className)}>
      <LogoMark />
      <span
        className={cn(
          "text-lg font-extrabold tracking-tight",
          tone === "hero" ? "text-hero-foreground" : "text-foreground",
        )}
      >
        Skillora
      </span>
    </span>
  );
}

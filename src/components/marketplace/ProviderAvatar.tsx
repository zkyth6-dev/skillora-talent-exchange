import { cn } from "@/lib/utils";

export function initials(name: string) {
  return name
    .split(/\s+/)
    .filter(Boolean)
    .slice(0, 2)
    .map((p) => p[0]?.toUpperCase())
    .join("");
}

export function ProviderAvatar({ name, className }: { name: string; className?: string }) {
  return (
    <span
      aria-hidden="true"
      className={cn(
        "inline-flex size-9 shrink-0 items-center justify-center rounded-full bg-gradient-brand text-xs font-bold text-primary-foreground ring-2 ring-card",
        className,
      )}
    >
      {initials(name)}
    </span>
  );
}

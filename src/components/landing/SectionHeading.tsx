import type { ReactNode } from "react";
import { cn } from "@/lib/utils";

export function SectionHeading({
  title,
  subtitle,
  action,
  className,
  align = "left",
}: {
  title: string;
  subtitle?: string;
  action?: ReactNode;
  className?: string;
  align?: "left" | "center";
}) {
  return (
    <div
      className={cn(
        "flex flex-col gap-4 sm:flex-row sm:items-end sm:justify-between",
        align === "center" && "items-center text-center sm:flex-col sm:items-center",
        className,
      )}
    >
      <div className={cn("max-w-2xl", align === "center" && "mx-auto")}>
        <h2 className="text-2xl font-bold sm:text-3xl md:text-4xl">{title}</h2>
        {subtitle && <p className="mt-3 text-base text-muted-foreground md:text-lg">{subtitle}</p>}
      </div>
      {action}
    </div>
  );
}

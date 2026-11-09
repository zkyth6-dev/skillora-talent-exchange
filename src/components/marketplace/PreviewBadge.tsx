import { FlaskConical } from "lucide-react";
import { useI18n } from "@/lib/i18n";
import { cn } from "@/lib/utils";

/** Required on every surface that renders development preview data. */
export function PreviewBadge({ className, withNote = false }: { className?: string; withNote?: boolean }) {
  const { m } = useI18n();
  return (
    <div className={cn("flex flex-wrap items-center gap-2", className)}>
      <span className="inline-flex items-center gap-1.5 rounded-full border border-warning/40 bg-warning/10 px-2.5 py-1 text-xs font-semibold text-foreground">
        <FlaskConical className="size-3.5 text-warning" aria-hidden="true" />
        {m.preview.badge}
      </span>
      {withNote && <span className="text-xs text-muted-foreground">{m.preview.note}</span>}
    </div>
  );
}

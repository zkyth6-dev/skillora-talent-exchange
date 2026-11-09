<!-- LOVABLE:BEGIN -->
> [!IMPORTANT]
> This project is connected to [Lovable](https://lovable.dev). Avoid rewriting
> published git history — force pushing, or rebasing/amending/squashing commits
> that are already pushed — as it rewrites history on Lovable's side and the
> user will likely lose their project history.
>
> Commits you push to the connected branch sync back to Lovable and show up in
> the editor, so keep the branch in a working state.

## What Lovable manages

Rules outside this block are the project's own. Change the pieces below in
Lovable, not by hand. With the [Lovable MCP](https://mcp.lovable.dev)
connected, `send_message` asks Lovable to do it; otherwise ask the user to
use the editor.

- **Database.** Do not add or edit `supabase/migrations/` or `drizzle/`.
  Ask Lovable to change the schema.
- **Generated files.** Do not edit a file whose header says it is automatically
  generated.
- **AI gateway.** Keep model calls on Lovable's gateway, and keep
  `LOVABLE_API_KEY` out of browser code.
- **Connectors.** Lovable provides native connectors. `list_connectors` shows
  which are enabled; `add_connector` only returns the dashboard link. When a
  connector is enabled, calls go through the connector gateway and need
  `LOVABLE_API_KEY`. When Lovable does not provide one, users can bring their
  own key and store it in Secrets.
- **Secrets.** Lovable manages them. Do not put secret values in the repo.
<!-- LOVABLE:END -->

## Skillora architecture rules

- All UI copy lives in `src/lib/i18n/messages/{en,id}.ts` and is read via `useI18n().m`; `id.ts` is typed against `Messages` so missing keys fail typecheck. Why: translation-ready UI without hardcoded strings.
- Money, numbers, dates, country and language names are formatted only through `src/lib/format.ts` (Intl), with amounts stored as integer minor units plus an ISO currency. Why: locale-correct display and no float drift.
- Platform defaults (commission rate, base currency, locales) live in `src/lib/platform-config.ts`; once the admin panel exists, the `platform_settings` table overrides them. Fee math goes through `calculateOrderFees`. Why: one configurable source for fees shown to users.
- Development sample listings live only in `src/data/preview-services.ts` and every surface rendering them shows `<PreviewBadge />`. Why: never present fabricated listings, reviews or earnings as real.
- Marketplace search/filter/sort/pagination is the pure `filterServices` in `src/lib/marketplace-filter.ts`; `/services` URL params are sanitised by `parseServicesSearch`. Why: testable logic and safe URL input.
- Theme is a `.dark` class on `<html>` set pre-paint by `THEME_INIT_SCRIPT`; locale preference is applied after hydration. Why: no theme flash and no hydration mismatch.
- Shared layout (header/footer, providers) lives in `src/routes/__root.tsx`; page sections live in `src/components/<area>/`.

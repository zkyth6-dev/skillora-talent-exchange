import { useEffect, useState } from "react";

export const THEME_STORAGE_KEY = "skillora.theme";

/** Runs before paint (inlined in <head>) so the stored theme never flashes. */
export const THEME_INIT_SCRIPT = `(function(){try{var t=localStorage.getItem('${THEME_STORAGE_KEY}');var d=t?t==='dark':window.matchMedia('(prefers-color-scheme: dark)').matches;if(d)document.documentElement.classList.add('dark');}catch(e){}})();`;

export function useTheme() {
  const [isDark, setIsDark] = useState(false);

  useEffect(() => {
    setIsDark(document.documentElement.classList.contains("dark"));
  }, []);

  const toggle = () => {
    const next = !document.documentElement.classList.contains("dark");
    document.documentElement.classList.toggle("dark", next);
    window.localStorage.setItem(THEME_STORAGE_KEY, next ? "dark" : "light");
    setIsDark(next);
  };

  return { isDark, toggle };
}

import { describe, expect, it } from "vitest";
import { PREVIEW_SERVICES } from "@/data/preview-services";
import { calculateOrderFees } from "@/lib/fees";
import { filterServices } from "@/lib/marketplace-filter";
import { PLATFORM_DEFAULTS } from "@/lib/platform-config";
import { parseServicesSearch } from "@/lib/services-search";

describe("platform commission", () => {
  it("defaults to 5% per completed order", () => {
    expect(PLATFORM_DEFAULTS.commissionRate).toBe(0.05);
  });

  it("takes $5.00 commission on a $100.00 order, leaving $95.00", () => {
    const f = calculateOrderFees(10000);
    expect(f.commissionMinor).toBe(500);
    expect(f.providerNetMinor).toBe(9500);
  });

  it("uses a configured rate when the admin changes it", () => {
    expect(calculateOrderFees(10000, 0.1).commissionMinor).toBe(1000);
  });
});

describe("marketplace filters", () => {
  it("filters by category", () => {
    const r = filterServices(PREVIEW_SERVICES, { category: "translation" }, 100);
    expect(r.items.length).toBeGreaterThan(0);
    expect(r.items.every((s) => s.category === "translation")).toBe(true);
  });

  it("filters by price range in major units", () => {
    const r = filterServices(PREVIEW_SERVICES, { minPrice: 50, maxPrice: 100 }, 100);
    expect(r.items.every((s) => s.priceFromMinor >= 5000 && s.priceFromMinor <= 10000)).toBe(true);
    expect(r.items.length).toBeGreaterThan(0);
  });

  it("filters by maximum delivery days and minimum rating", () => {
    const r = filterServices(PREVIEW_SERVICES, { maxDelivery: 3, minRating: 4.8 }, 100);
    expect(r.items.every((s) => s.deliveryDays <= 3 && s.rating >= 4.8)).toBe(true);
  });

  it("search returns relevant results", () => {
    const r = filterServices(PREVIEW_SERVICES, { q: "logo" }, 100);
    expect(r.items.length).toBeGreaterThan(0);
    expect(r.items.every((s) => /logo/i.test(s.title + s.tags.join(" ")))).toBe(true);
  });

  it("sorts by price ascending", () => {
    const r = filterServices(PREVIEW_SERVICES, { sort: "price_asc" }, 100);
    const prices = r.items.map((s) => s.priceFromMinor);
    expect(prices).toEqual([...prices].sort((a, b) => a - b));
  });

  it("paginates 9 per page", () => {
    const p1 = filterServices(PREVIEW_SERVICES, {});
    const p2 = filterServices(PREVIEW_SERVICES, { page: 2 });
    expect(p1.items).toHaveLength(9);
    expect(p2.items).toHaveLength(PREVIEW_SERVICES.length - 9);
    expect(p1.pageCount).toBe(2);
  });
});

describe("services URL search sanitising", () => {
  it("drops unknown categories and invalid numbers", () => {
    expect(parseServicesSearch({ category: "hacking", min: "-5", sort: "evil" })).toEqual({});
  });

  it("keeps valid values", () => {
    expect(parseServicesSearch({ q: " logo ", category: "seo", min: "10", page: "2" })).toEqual({
      q: "logo",
      category: "seo",
      min: 10,
      page: 2,
    });
  });
});

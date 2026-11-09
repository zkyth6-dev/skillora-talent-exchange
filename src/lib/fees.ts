import { PLATFORM_DEFAULTS } from "./platform-config";

export interface FeeBreakdown {
  /** Order amount in minor units (e.g. cents). */
  subtotalMinor: number;
  /** Platform commission in minor units. */
  commissionMinor: number;
  /** What the provider receives after commission, in minor units. */
  providerNetMinor: number;
  /** Rate applied, e.g. 0.05. */
  rate: number;
}

/**
 * Transparent commission calculation for a completed order.
 * Works in integer minor units to avoid floating point drift.
 */
export function calculateOrderFees(
  subtotalMinor: number,
  rate: number = PLATFORM_DEFAULTS.commissionRate,
): FeeBreakdown {
  if (!Number.isInteger(subtotalMinor) || subtotalMinor < 0) {
    throw new Error("subtotalMinor must be a non-negative integer");
  }
  if (rate < 0 || rate > 1) {
    throw new Error("rate must be between 0 and 1");
  }
  const commissionMinor = Math.round(subtotalMinor * rate);
  return {
    subtotalMinor,
    commissionMinor,
    providerNetMinor: subtotalMinor - commissionMinor,
    rate,
  };
}

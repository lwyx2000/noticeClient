# Design System Strategy: The Digital Vault

## 1. Overview & Creative North Star
The North Star for this design system is **"The Digital Vault."** We are not building a generic chat app; we are crafting a high-end, private environment for information management. The aesthetic is defined by an architectural approach to depth, where the interface feels carved out of a singular, dark crystalline block.

To move beyond "template" looks, we utilize **intentional asymmetry** and **tonal layering**. We reject the flat, grid-heavy "dashboard" look in favor of an editorial layout that prioritizes content density through negative space rather than lines. Information flows across varying levels of luminosity, creating a UI that feels deep, expensive, and incredibly focused.

## 2. Colors
Our palette is a sophisticated interplay of deep indigos and electric purples. The goal is to use color not as a decoration, but as a functional beacon within a dark void.

*   **Foundation:** The core experience lives in `background` (#060e20). This isn't a "grey-black"—it’s a deep cosmic blue that provides a richer canvas for the `primary` (#9fa7ff) and `secondary` (#c180ff) accents.
*   **The "No-Line" Rule:** We explicitly prohibit 1px solid borders for sectioning. Structural definition must be achieved through background shifts. Use `surface_container_low` for secondary sections and `surface_container_high` for interactive cards.
*   **Surface Hierarchy & Nesting:** Depth is physical. A 'Pinned' message shouldn't just have an icon; it should sit on a `surface_container_highest` (#192540) to physically "lift" it toward the user.
*   **The "Glass & Gradient" Rule:** For floating elements like bottom navigation or action sheets, use semi-transparent `surface` colors with a 20px backdrop blur. 
*   **Signature Textures:** Apply a subtle linear gradient from `primary` to `secondary` on high-value CTAs. This creates a "shimmer" effect that signals premium interactivity.

## 3. Typography
We utilize a dual-font system to create an editorial cadence.

*   **Display & Headlines (Manrope):** Use Manrope for all `display` and `headline` tiers. Its geometric yet friendly curves feel modern and engineered. Large `headline-lg` titles should be used with generous leading to anchor pages.
*   **Body & Labels (Inter):** Inter is our workhorse for readability. Its tall x-height ensures that long-form messages remain legible even at `body-sm` (0.75rem).
*   **Hierarchy as Identity:** Use `title-lg` in `on_surface` for message headers, while using `label-md` in `on_surface_variant` (#a3aac4) for metadata like timestamps. This 20% contrast gap creates an immediate visual priority without clutter.

## 4. Elevation & Depth
In "The Digital Vault," we replace shadows with **Luminous Stacking**.

*   **The Layering Principle:** Instead of traditional shadows, stack containers:
    *   Level 0: `surface` (The Base)
    *   Level 1: `surface_container` (Content Groups)
    *   Level 2: `surface_container_high` (Interactive Elements)
*   **Ambient Shadows:** When an element must float (e.g., a modal), use a shadow tinted with `surface_tint` (#9fa7ff) at 8% opacity with a 32px blur. It should look like a soft glow, not a dark smudge.
*   **The "Ghost Border" Fallback:** If accessibility requires a border, use `outline_variant` (#40485d) at 15% opacity. It should be felt, not seen.
*   **Glassmorphism:** Use `primary_container` with 40% opacity and a backdrop blur for active states or "unread" indicators to create a "glowing glass" effect.

## 5. Components

### Cards & Lists (The Core)
*   **Rule:** Forbid divider lines. Use `spacing.4` (1rem) of vertical white space to separate messages.
*   **Pinned States:** Use `surface_container_highest` for the background and a `tertiary` (#c6fff3) left-accent bar (4px wide) to denote priority.
*   **Cache Management:** Use a progress bar styled with a gradient from `primary` to `tertiary_dim` to visualize storage.

### Buttons
*   **Primary:** Rounded `full` (pill shape). Background: `primary_fixed`. Text: `on_primary_fixed`.
*   **Secondary:** Ghost style. No background, `outline_variant` at 20% opacity, text in `primary`.
*   **States:** On hover/press, shift the background to `primary_fixed_dim`.

### Input Fields
*   **Styling:** Use `surface_container_lowest` (#000000) for the input well to create a "sunken" feel. 
*   **Rounding:** Use `rounded.md` (0.75rem) for a sleek, modern touch that matches the mobile hardware.

### Chips
*   **Action Chips:** Use `surface_bright` with `on_surface` text for high contrast. Perfect for "Clear Cache" or "Archive" actions.

## 6. Do's and Don'ts

### Do
*   **Do** use `tertiary` (#c6fff3) for "Success" or "Complete" states in cache management; its minty tone feels fresher than standard green.
*   **Do** lean into `rounded.xl` (1.5rem) for large container groupings to mimic the hardware corners of modern smartphones.
*   **Do** use `primary_dim` for icons to ensure they feel part of the environment, not stuck on top of it.

### Don'ts
*   **Don't** use pure white (#FFFFFF) for text. Always use `on_surface` (#dee5ff) to reduce eye strain in dark mode.
*   **Don't** use `error` (#ff6e84) for non-critical alerts. Reserve it strictly for data loss or destructive cache clearing.
*   **Don't** use standard 1px dividers. If you feel the need for a line, your spacing or background tonal shift isn't strong enough. Fix the hierarchy first.
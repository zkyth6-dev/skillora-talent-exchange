# Skillora Talent Exchange

MASTER PROJECT PROMPT — SKILLORA



Global Freelance & Digital Services Marketplace



Act as a senior product designer, UI/UX architect, full-stack software engineer, database architect, cybersecurity specialist, and startup product strategist.



Your mission is to design and build a premium, production-oriented global freelance marketplace called SKILLORA.



Do not create a generic template, static landing page, or simple earning-rewards application.



Build a real, functional, scalable digital services marketplace that connects people who have skills with people and businesses that need those skills.



1. BRAND IDENTITY



Brand name: SKILLORA



Brand positioning:

A global digital services and freelance marketplace for everyone.



Main tagline:

"Your Skills. Your Opportunity."



Alternative messaging:

"Turn Your Skills Into Opportunities."



Brand philosophy:

Anyone with a useful skill should have an opportunity to offer their services, find customers, build a reputation, and earn money through legitimate work.



Brand personality:



- Premium

- Modern

- Professional

- International

- Trustworthy

- Innovative

- Inclusive



Do not make the platform exclusively Indonesian. Design it for international users from the beginning.



2. TARGET AUDIENCE



The platform must be accessible to:



- Students

- University students

- Unemployed individuals

- Freelancers

- Independent professionals

- Content creators

- Entrepreneurs

- Small and medium businesses

- Remote workers

- Anyone with a marketable skill



Do not restrict participation based on professional status.



Support appropriate age restrictions, identity verification, payment eligibility, and local legal requirements.



3. PLATFORM OBJECTIVE



Build a two-sided marketplace.



USER TYPE A — SERVICE PROVIDER:

Users who want to earn money by offering their skills.



USER TYPE B — CLIENT:

Users or businesses who need services.



One account must be capable of acting as both a client and a service provider.



4. SERVICE CATEGORIES



Create a scalable category system including:



- Graphic Design

- Logo and Branding

- Video Editing

- Photography

- Illustration

- Web Development

- Mobile App Development

- UI/UX Design

- Programming

- AI Services

- Content Writing

- Translation

- Data Entry

- Virtual Assistance

- Social Media Management

- Digital Marketing

- SEO

- Audio Production

- Animation

- Business Consulting

- Education and Tutoring

- Other Custom Services



Allow administrators to add, edit, and manage categories.



5. PREMIUM DESIGN SYSTEM



Create a visually impressive international SaaS marketplace.



Design direction:

Premium technology startup aesthetic.



Primary color:

Midnight Navy (#0B1220)



Secondary color:

Deep Blue (#142C50)



Accent:

Electric Blue (#3478F6)



Highlight:

Cyan (#42D9F5)



Neutral:

White, light gray, charcoal.



Typography:

Use a clean modern font such as Inter or Plus Jakarta Sans.



Design requirements:



- Responsive layouts

- Desktop, tablet, and mobile support

- Elegant cards

- Clean typography hierarchy

- Consistent spacing

- Subtle shadows

- Soft gradients

- Smooth transitions

- Premium buttons

- Accessible color contrast

- Skeleton loading states

- Empty states

- Error states

- Light and dark themes



Avoid excessive glassmorphism, unnecessary animations, clutter, and generic template aesthetics.



6. MAIN APPLICATION PAGES



Create the following pages:



A. Landing Page



- Premium hero section

- Brand introduction

- Search services

- Popular categories

- Featured services

- Featured freelancers

- How it works

- Benefits

- Trust and safety information

- Call-to-action sections

- Footer with international links



B. Authentication



- Sign up

- Login

- Password recovery

- Email verification

- Secure session management

- Role selection

- Onboarding



C. User Dashboard



- Profile overview

- Active orders

- Completed orders

- Earnings summary

- Notifications

- Recent activities

- Account settings



D. Marketplace



- Browse services

- Search

- Filters

- Categories

- Price range

- Delivery time

- Seller ratings

- Sorting

- Pagination



E. Service Detail



- Service description

- Provider information

- Portfolio

- Pricing packages

- Delivery estimate

- Reviews

- FAQs

- Order button



F. Provider Dashboard



- Earnings overview

- Published services

- Active orders

- Completed orders

- Pending work

- Portfolio manager

- Service analytics

- Profile completion



G. Client Dashboard



- Current orders

- Order history

- Saved providers

- Messages

- Reviews

- Transaction history



H. Messaging



- Buyer-provider conversations

- Order-related messages

- Message timestamps

- Attachment support with validation

- Report functionality



I. Order Management



- Order creation

- Requirements submission

- Order status

- Delivery submission

- Revision requests

- Client approval

- Cancellation handling

- Dispute reporting



J. Wallet and Financial Dashboard



- Earnings records

- Transaction history

- Platform fees

- Withdrawal eligibility

- Payment status

- Currency display



Do not implement a fake wallet or claim that real funds are held unless a licensed payment provider and compliant financial infrastructure are integrated.



K. User Profile



- Profile photo

- Biography

- Skills

- Languages

- Location

- Portfolio

- Completed jobs

- Ratings

- Reviews

- Verification status



L. Administration Panel



- User management

- Service moderation

- Category management

- Order monitoring

- Reports

- Disputes

- Platform analytics

- Revenue analytics

- Platform settings

- Audit logs



7. BUSINESS MODEL



SKILLORA must support several revenue streams:



1. Transaction commission.

2. Optional premium subscriptions.

3. Featured service promotions.

4. Business accounts.

5. Additional professional tools.



Example initial commission:

5% per successfully completed order.



Make the commission configurable through the admin panel.



Clearly display all applicable fees before a transaction.



Never hide fees or create misleading earnings claims.



8. INTERNATIONALIZATION



Build the architecture for international expansion.



Requirements:



- English as the default language.

- Indonesian as an additional language.

- Translation-ready interface.

- Currency formatting.

- Timezone-aware timestamps.

- Country selection.

- International phone number formatting.

- Locale-aware date and number formatting.



Do not assume all users use USD or IDR.



Use a proper localization system instead of hardcoding text throughout the application.



9. TECHNOLOGY STACK



Use a modern and maintainable technology stack.



Preferred:



- Next.js

- React

- TypeScript

- Tailwind CSS

- Supabase

- PostgreSQL

- Supabase Authentication

- Supabase Storage



Deployment options:



- Cloudflare Pages or another suitable hosting provider.

- Vercel where appropriate.



Choose the architecture according to the actual deployment compatibility.



Use free-tier services during initial development whenever possible.



Do not assume free-tier services provide unlimited usage.



10. DATABASE ARCHITECTURE



Create properly structured database tables for:



- profiles

- user_roles

- services

- categories

- portfolios

- orders

- order_deliveries

- conversations

- messages

- reviews

- favorites

- notifications

- transactions

- subscriptions

- reports

- disputes

- platform_settings

- audit_logs



Include:



- Primary keys

- Foreign keys

- Indexes

- Timestamps

- Appropriate constraints

- Row Level Security

- Data validation



Never expose service-role credentials or private secrets in frontend code.



11. SECURITY AND TRUST



Implement security best practices:



- Secure authentication

- Authorization checks

- Database Row Level Security

- Input validation

- Output sanitization

- Rate limiting

- File upload restrictions

- Protection against common web vulnerabilities

- Secure environment variables

- Admin permission separation

- Audit logging

- Reporting and moderation



Do not trust frontend-only authorization.



Protect users from scams, impersonation, abusive behavior, and suspicious transactions.



12. PAYMENT ARCHITECTURE



Design an integration-ready payment architecture.



Support future integration with appropriate payment providers.



Do not invent payment API keys, fake transaction confirmations, or fake payout functionality.



For the MVP, allow order creation and tracking without processing real payments.



For production:



- Verify payments server-side.

- Use signed webhooks.

- Handle duplicate events safely.

- Record transaction status.

- Handle refunds and disputes according to provider rules.

- Support regional payment restrictions.

- Ensure age and identity eligibility.



13. USER EXPERIENCE



Every interaction must have a meaningful function.



Buttons must work.

Forms must validate.

Navigation must work.

Search must return relevant results.

Filters must actually filter results.

Dashboard figures must come from real application data.



Never use fake earnings or fabricated reviews in production.



Provide realistic demo data only in clearly marked development environments.



14. PERFORMANCE AND RESPONSIVENESS



Optimize for:



- Mobile-first usability

- Fast page loading

- Responsive image handling

- Lazy loading

- Efficient database queries

- Reusable components

- Accessible interactions

- Search engine optimization



The application should work on modern mobile and desktop browsers.



Build a Progressive Web App where appropriate.



15. DEVELOPMENT ROADMAP



Build the project in phases.



PHASE 1 — FOUNDATION



- Project setup

- Design system

- Navigation

- Landing page

- Authentication

- User profiles



PHASE 2 — MARKETPLACE



- Service creation

- Service listing

- Search

- Categories

- Filters

- Provider profiles



PHASE 3 — ORDER SYSTEM



- Order creation

- Order status

- Messaging

- Delivery

- Reviews



PHASE 4 — DASHBOARDS



- Client dashboard

- Provider dashboard

- Admin dashboard

- Transaction records



PHASE 5 — MONETIZATION



- Commission logic

- Subscription architecture

- Payment provider integration

- Withdrawal workflows



PHASE 6 — INTERNATIONAL EXPANSION



- Multiple languages

- Multiple currencies

- Regional configuration

- Advanced analytics



16. DEVELOPMENT RULES



IMPORTANT:



1. Build real functional features, not merely attractive screenshots.

2. Do not create fake buttons.

3. Do not hardcode fake user earnings.

4. Do not expose confidential credentials.

5. Do not skip security or database authorization.

6. Use reusable and maintainable components.

7. Keep the design consistent throughout all pages.

8. Explain required environment variables.

9. Provide database migrations and setup instructions.

10. Test important user flows.

11. Clearly identify features that are still mockups or incomplete.

12. Never claim that payment, authentication, or deployment works without testing it.



17. FINAL PRODUCT VISION



SKILLORA should feel like a serious global technology company.



The experience should be premium, intuitive, reliable, and accessible.



A person should be able to join, showcase a skill, publish a service, find customers, complete work, and build a professional reputation.



A client should be able to discover talented people, compare services, communicate, place orders, and review completed work.



The platform owner should be able to manage the ecosystem and build sustainable revenue through transparent business models.



Start by creating the project architecture, design system, and complete responsive landing page.



Then proceed through the roadmap without sacrificing functionality, security, or design quality.

This project was built with [Lovable](https://lovable.dev).

**Live app**: https://skillora-talent-exchange.lovable.app

## Build with Lovable

Continue developing this project in the [Lovable editor](https://lovable.dev/projects/7ba8c3e3-34c0-4ed5-956b-f02b93073004).

- **Ship faster**: describe what you want to build and Lovable handles the code.
- **Stay in sync**: every change made in Lovable is committed straight to this repository.
- **Full ownership**: this code is yours. Push to `main` on GitHub and your changes sync back into Lovable, ready for your next prompt.

## Development

Prefer working locally? You need Node.js and npm — [install with nvm](https://github.com/nvm-sh/nvm#installing-and-updating).

```sh
git clone <this-repository-url>
cd <repository-name>
npm i
npm run dev
```

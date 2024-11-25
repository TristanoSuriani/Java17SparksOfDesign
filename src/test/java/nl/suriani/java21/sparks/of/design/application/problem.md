Here's a challenging and interesting problem for you to solve using your relational library. This problem will require you to leverage relationships, indirect queries, and perhaps some recursive logic, pushing your library's capabilities and giving you a sense of how powerful relational modeling can be.

### Challenge: Dynamic Family Subscription Plan
Imagine you’re running a subscription service that offers "Family Plans" allowing multiple subscribers to share access. However, the rules are a bit complex:

**Primary Subscriber**: Each family plan has one primary subscriber who holds the main subscription.

**Family Members**: Family members can be added to the plan and gain access to the primary subscriber’s features.
**Limits on Family Members**: The number of family members allowed depends on the type of plan:


**Basic Family Plan**: Allows up to 3 family members.

**Premium Family Plan**: Allows up to 5 family members.

**Tiered Features**: Some features are restricted even for family members. For example:

**Only the primary subscriber** can access "Exclusive Content".

**Family members can access "Streaming" and "Downloads"** if the primary plan includes them.

**Referrals by Family Members**: If a family member refers another subscriber who joins with a separate paid plan, the family receives a discount on their plan. The discount is cumulative (5% per referred new subscriber) but capped at 20%.
Your task is to use your relational library to model this scenario and answer the following questions**:

## Questions to Solve

Can a Subscriber Join a Family Plan?

Given a family plan and a subscriber, check if the subscriber can join the plan without exceeding the family member limit.
List All Features Available to a Family Member

Given a family member and a family plan, determine which features the family member can access.
Calculate the Family’s Effective Discount

Given a family plan and a list of subscribers referred by family members, calculate the family’s effective discount, respecting the cap at 20%.
Check Access to Exclusive Content

Given a family member, check if they have access to "Exclusive Content".

## Suggested Steps
Here’s how you might approach solving this challenge:

**Define Relationships**:

PrimarySubscriberOf(plan, subscriber)
FamilyMemberOf(plan, subscriber)
FeatureOf(plan, feature)
ReferredSubscriber(referred, referrer)
ReferralDiscount(plan, discount)
Implement Methods in Your Library:

**checkFamilyMemberLimit**: A method to check if adding a new family member exceeds the limit based on the plan type.

**getFamilyMemberFeatures**: A method to retrieve the features available to a family member, considering restrictions like "Exclusive Content" only for the primary subscriber.

**calculateDiscount**: A method to calculate the cumulative discount based on referrals, respecting the 20% cap.

**hasExclusiveContentAccess**: A method to check if a family member has access to "Exclusive Content" (hint: only the primary subscriber should have access).


## Test Cases:

**Adding Family Members**: Create test cases to check if your library enforces the family member limit correctly.
**Feature Access for Members**: Test scenarios where family members access "Streaming" and "Downloads" but not "Exclusive Content".
**Referral Discounts**: Test discount calculation with various numbers of referrals to ensure it caps at 20%.
Strategy Pattern
Our project uses the Strategy pattern to calculate upgrade prices in different ways. 
The CostStrategy interface defines pricing, and LinearCost and ExponentialCost implement 
different calculations. Each upgrade receives a pricing strategy when it’s created. This 
lets us change how its price grows without changing the upgrade’s purchase logic.

Observer Pattern
We used the Observer pattern to keep the interface updated when the game changes. GameState
manages a list of listeners through the GameListener interface, and CoinPanel and ShopPanel
register as listeners. When coins change or an upgrade is purchased, the game notifies these 
panels so they can update the displayed balance, prices, and buttons.

Template Method Pattern
We used the Template Method pattern in the Upgrade class. Its purchase() method defines the 
shared steps for buying an upgrade: attempt to spend coins, then increase the upgrade level 
and apply its effect only if spending succeeds. The subclasses supply different effects: 
ClickPowerUpgrade improves clicking, AutoClickerUpgrade adds passive income, and MultiplierUpgrade
multiplies earnings. This avoids repeating the purchase process in every upgrade class.
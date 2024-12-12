# NoThanks

本项目实现了一个不谢牌的游戏。

Made for HLY-302.

## 游戏规则

### 前置资源

若干张牌（标有从1到 x （default: 10）的数字）

若干枚硬币

若干位玩家（default: 3）

### 游戏阶段

#### 前置阶段

抽取 x （default: 3）张牌，将其从牌堆中除去。

每位玩家发放 x （default: 4）枚硬币。

#### 进行阶段

依次从牌堆中翻出一张牌，从标记位（上一轮中取走了牌的玩家 的下一位玩家）玩家起轮询：

每位玩家可以选择要或不要该牌：如果要，则拿走该牌及其上面的所有硬币；如果不要，则须放置一枚硬币，使该牌继续轮转。

#### 结算阶段

玩家的手牌，若有连续数字的则按照最小数字计算，将所有牌上的数字相加，并减去玩家手中的硬币数量，得到每位玩家的最终得分。

根据得分从小到大排名。

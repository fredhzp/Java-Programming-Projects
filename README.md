# Java Programming Projects 🚀

A collection of three diverse Java applications showcasing object-oriented programming, game development, and algorithm implementation.

## Table of Contents
- [Projects](#projects)
  - [1. Shape Analyzer](#1-shape-analyzer)
  - [2. Pebble Game](#2-pebble-game)
  - [3. Tron Light Cycles](#3-tron-light-cycles)
- [Installation](#installation)
- [Technical Details](#technical-details)
- [License](#license)

## Projects

### 1. Shape Analyzer
**Determines which regular shape has the largest bounding box area**

#### Features:
- 📐 Supports multiple regular shapes (circle, triangle, square, hexagon)
- 📦 Calculates bounding box areas
- 📁 Loads shapes from text files
- 🏗️ Object-oriented design with inheritance

#### File Format Example:

C 0 0 5.0 // Circle with radius 5 at origin
T 2 2 4.0 // Triangle with side 4 at (2,2)
H 1 1 3.0 // Hexagon with side 3 at (1,1)


### 2. Pebble Game
**A strategic two-player board game**

#### Features:
- ⚫⚪ Turn-based pebble movement mechanics
- 🎚️ Adjustable board sizes (3x3, 4x4, 6x6)
- ⏱️ Automatic turn counting (5n turns)
- 🏆 Win/draw detection and auto-restart
- 🖥️ Graphical interface with message boxes

#### Game Rules:
- Players alternate moving their pebbles
- Movement affects neighboring pebbles
- Goal: Remove opponent's pebbles
- Winner has more pebbles after 5n turns

### 3. Tron Light Cycles
**The classic light cycle battle game**

#### Features:
- 🏍️ Two-player competitive gameplay
- ⌨️ Keyboard controls (WASD and Arrow keys)
- 🎨 Customizable player colors
- 📊 Score tracking database
- � Highscore table (top 10 players)
- 🔄 Game restart functionality

#### How to Play:
- Avoid walls and light trails
- Last surviving player wins
- Names and colors customizable

## Installation

1. **Requirements**:
   - Java JDK 11+
   - (For Tron) SQLite for score tracking

2. **Clone the repository**:
   ```bash
   git clone https://github.com/fredhzp/java-projects.git

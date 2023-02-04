# Pixabay-search

Pixabay search concept

- [Overview](#overview)
- [Architecture](#architecture)
- [3rd party libraries](#3rd-party-libraries)
- [Known issues](#known-issues)

<img src=" height="500">

## Overview

Applications searches images in pixabay

### Features

+ Fetching results of search via Pixabay API
+ Displaying different positions from my resume

## Architecture

Simple MMVM/MVI

### Packaging

Project is structurized to preserve clean architecure in the project. Main independent modules ui,
domain and date. Additionally for different purposes, in project have been created independent
feature modules.

### Domain

Main business models of all used entities is kept in `domain` module. `Domain` module does not
contain any business logic.

### Data

Main business logic lives in the `data` module. `Data` is responsible for downloading data
from `Repository` and combine them with network status.
`Repository` is implemented with two data sources. In memory cache and remote, for fetching data
from network. Remote data source is responsible for work with `ApiService` - Retrofit interface to
define network calls.

### Coroutines

Coroutines is used to perform simple api calls defined in through `Repository`.

### View

### ViewModel

`ViewModel` is build on top of Android Architecture Components. It gives simplicity when it comes to
screen rotation as it survives `Fragment` or `Activity` re-creation. Main responsibility of
a `ViewModel` is to react to specific data changes and modify state of UI. Updating `ViewState` is
automatically translated to UI via renderer classes

## 3rd party libraries

- Retrofit2 (Networking).
- Coil (Image loading).
- Coroutines (For async stuff)
- Android View Binding
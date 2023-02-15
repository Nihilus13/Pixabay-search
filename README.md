# Pixabay-search

Pixabay search concept

- [Overview](#overview)
- [Architecture](#architecture)
- [3rd party libraries](#3rd-party-libraries)
- [Snapshot tests](#snapshot-tests)
- [Known issues](#known-issues)

<img src="pixabay.gif" height="500">

## Overview

Applications searches images in pixabay

### Features

+ Fetching results of search via Pixabay API
+ Displaying details of Pixabay search

## Architecture

Simple MVVM/MVI

### Packaging

Project is structurized to preserve clean architecure in the project. Main independent modules ui,
domain and date. Additionally for different purposes, in project have been created independent
feature modules.

### Domain

Main business models of all used entities is kept in `domain` module. `Domain` module does not
contain any business logic.

### Data

Main business logic lives in the `data` module. `Data` is responsible for downloading data
from `PixabayRepository` and combine them with network status.
`PixabayRepository` is implemented with two data sources. In memory cache and remote, for fetching
data from network. Remote data source is responsible for work with `SearchService` - Retrofit
interface to define network calls.

### Coroutines

Coroutines is used to perform simple api calls defined in through `PixabayRepository`.

### View

Thanks to Android Data Binding mechanism `Activity` or `Fragment` acts as a Dummy View. It does not
contain any presentation or render logic. `View` is only responsible for getting an instance
of `ViewState` and render it in `Render` classes. List of images is implement with usage of adapter
delegate pattern.

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

## Snapshot tests

- View snapshot tests need to be run in particular type of emulator to record and compare screen
  captures. It has been implemented with usage of karumi shot library.

## Known issues

- Missing simple CircleCI integration for showing
- Lack of jacoco configuration to show coverage report of repository.

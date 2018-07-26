# Calculator

Android Calculator using MVVM + DataBinding



### Environments & Architecture

- AndroidStudio 3.2 Beta 3
- minSdkVersion `21`
- targetSdkVersion `28`
- AndroidX + MVVM + DataBinding
- Isolate calculator features from Android Framework for unit test



### Dependencies (except defaults)

- Android KTX `1.0.0-beta01`
- AssertJ Core `3.9.0`



### Features

- Active Calculator : Expression is evaluated if evaluatable whenever input is `+` `-` `×` `÷` `=`
- Passive Calculator : Expression is evaluated only if input is `=`
- Support recovering last opened activity (between active & passive calculator activity)
- Support recovering last displayed result (and initialized as first operand)
- Support orientation change with layout for landscape mode
- UnitTest with [`assertj-core:3.9.0`](https://github.com/joel-costigliola/assertj-core)



### Implementations

- Kept project structure as simple as possible
  - `.base` for class reusability through inheritance
  - `.common` for class share throughout `.active` and `.passive` components
  - `.active` for active calculator-related components
  - `.passive` for passive calculator-related components
  - `.model` for model classes `sealed` `enum` `data` `...`
  - `.util` for utility class - `Storage`

- No `RecyclerView` or `ListView` is used since,

  - calculator panel buttons are not recycled
  - adapter (both recycler and list) takes less advantage, though makes code hard to read
  - layout is static (fixed)
    - ConstraintLayout chain & layout element styling is enough

- Share Resources if possible, including data bound layouts

- Separate color resources by its value definition & behavior to color mapping



### Component Roles

- `Activity` as `View` : takes Activity transition control, Fragment management(with initialization), and calculator(model) initialization(through ViewModel)
- `Fragment` as `View` : binds given ViewModel to layout using 2-way data-binding
- `ViewModel` : delivers event & data between view and calculator(model)
- `Calculator` is considered as... more like `repository` since it holds multiple `models`
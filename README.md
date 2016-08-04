# lcmap.logger

[![Build Status][travis-badge]][travis][![Dependencies Status][deps-badge]][deps][![Clojars Project][clojars-badge]][clojars]

*LCMAP Unified Logging & Log Services*

[![LCMAP open source project logo][lcmap-logo]][lcmap-logo-large]


#### Contents

* [About](#about-)
* [Documentation](#documentation-)
* [Configuration](#configuration-)
* [Usage](#usage-)
* [License](#license-)


## About [&#x219F;](#contents)

The Land Change Monitoring Assessment and Projection (LCMAP) system will
provide the science community with real-time access to decades of Landsat
Analysis Ready Data (ARD). To make this system available to the public
scientific computing community, an API service will be provided. The
LCMAP REST service API is codified in this project and prototypes the
functionality necessary to support the public service.


## Documentation [&#x219F;](#contents)

The LCMAP Logger API reference is slowly being updated with docstrings. The
project's auto-generated documentation is available here:

* [http://usgs-eros.github.io/lcmap-logger](http://usgs-eros.github.io/lcmap-logger)


## Configuration [&#x219F;](#contents)

```ini
[lcmap.logging]

level = debug
namespaces = lcmap.rest,lcmap.config,lcmap.event,lcmap.data,lcmap.event,lcmap.see
```


## Usage [&#x219F;](#contents)

TBD


# License [&#x219F;](#contents)

Copyright © 2015-2016 United States Government

NASA Open Source Agreement, Version 1.3



<!-- Named page links below: /-->

[travis]: https://travis-ci.org/USGS-EROS/lcmap-logger
[travis-badge]: https://travis-ci.org/USGS-EROS/lcmap-logger.png?branch=master
[deps]: http://jarkeeper.com/usgs-eros/lcmap-logger
[deps-badge]: http://jarkeeper.com/usgs-eros/lcmap-logger/status.svg
[lcmap-logo]: https://raw.githubusercontent.com/USGS-EROS/lcmap-system/master/resources/images/lcmap-logo-1-250px.png
[lcmap-logo-large]: https://raw.githubusercontent.com/USGS-EROS/lcmap-system/master/resources/images/lcmap-logo-1-1000px.png
[clojars]: https://clojars.org/gov.usgs.eros/lcmap-logger
[clojars-badge]: https://img.shields.io/clojars/v/gov.usgs.eros/lcmap-logger.svg
[tag-badge]: https://img.shields.io/github/tag/usgs-eros/lcmap-logger.svg?maxAge=2592000
[tag]: https://github.com/usgs-eros/lcmap-logger/tags

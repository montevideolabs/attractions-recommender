# Attractions Recommender

This code is an approach to creating a complete software product on top of the prototype
recommendation systems developed in
https://github.com/mg-um/mastering-ml-on-aws/blob/master/chapter6/train_spark.ipynb.

Use `mvn install` for creating a fat jar containing all the dependencies compiled. In case
you don't want to include the Spark dependencies in your jar (as Spark will be provided by
the infrastructure) you can change the scope of the Spark dependency in [pom.xml](pom.xml)
to "provided".

### Structure

The recommender is split into three independent components:
* **etl -** reads the raw data, transforms it and loads it into the feeds that will then
be consumed by other components. Currently loads visits and attractions from Sigir data.
* **training -** reads the visits feed and trains the models. Currently the only supported
algorithm to train is Spark's ALS.
* **serving -** loads the models trained and exposes a recommendations service. Supports
different recommenders to coexist.

Configurations, feeds management and spark session management are isolated in a common
package, on which all the components depend.

### Configuration

Some aspects of the different components are driven by configurations, which are managed
in the common package. The values to be used for these configurable aspects should be set
as environment variables when running each component, allowing them to be specific to
different run environments.

### Feeds

The common package contains a simple framework for handling feeds, to which the components
should delegate their IO.

These feeds are stored in a file system, in a root path that should be provided via
configurations. By using this feed framework, different components can run completely
independently and easily synchronize using a file system acting as a "whiteboard".

Currently, local file system ("file://") and S3 ("s3://") are supported seamlessly. Also,
the feeds framework handles the versioning of the different updates in a feed using update
timestamps in a transparent way, enhancing the auditability of the system.

### Paneled Recommender

In order to support different recommenders to coexist in the recommendations service, we
provide a paneled implementation of the recommender that can use different implementations
for different panels. This can be used for doing A/B tests.

When a recommendation request comes, the paneled recommender applies a deterministic
hashing function on some characteristics of the requests and uses that hash to pick the
panel to which the request belongs. A few things to note:
- It is important that we can re-create the hashing
function used when analyzing recommendation logs to attribute behavior to the right
implementation.
- The natural pick in this case would be to hash the user id in the request. Even though
that's valid and useful, it is recommended to consider adding some "salt" to that so as to
not always include the same users as exposed in your experiments. In this case we are
using the minute-of-the-hour at the time of the request. Of course, you must be able to
re-create your salting when re-creating the hashing function for analysis.

### Drivers

A few driver apps are included for the different components. Even though these should be
helpful (especially the component-specific drivers that use feeds for synchronizing with
other components), one could also compose the units in the code in any way needed.

### Debugging

The common package contains a data frame descriptor utility that is only effective when
debug is configured to be enabled. This descriptor prints basic stats about the data, and
can be extended for any specific cases.

An IDE is also a great tool for debugging, and the way in which the components are
organized makes it easy to do interactive debugging of each piece in isolation.

FROM jaegertracing/all-in-one:1.28
COPY sampling_strategies.json  etc/jaeger/sampling_strategies.json


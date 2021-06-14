@Grab('org.rundeck.api:rd-api-client:1.3.9')
import org.rundeck.client.RundeckClient


if (args.length < 3) {
    System.err.println("Args: <RundeckUrl> <AuthToken> <Project>")
    System.exit(2)
}

def api = RundeckClient.with(args[0],args[1]).build().service
def call = api.listJobs(args[2], null)
def execute = call.execute()
if (execute.successful) {
    def body = execute.body()
    println(body.size() + " found:")
    body.each { jobItem -> println jobItem.id }
} else {
    System.err.println("Error: " + execute.code() + " " + execute.message())
    System.exit(2)
}

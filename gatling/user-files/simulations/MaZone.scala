
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class MaZone extends Simulation {
//	val feeder = csv("pages.csv").random

	val httpProtocol = http
		.baseURL("http://ec2-52-19-51-173.eu-west-1.compute.amazonaws.com:8080/LATEST/resources/")
		.inferHtmlResources()

	val scn = scenario("MaZone")
//		.feed(feeder)
		.exec(http("Get Page")
			.post("prises-en-charge")
			.body(StringBody("""{"localDateTime":"2016-01-16T16:45:53.272","etat":"Transport","lieuPrisEnCharge":"2.2830568718716964,48.76678531547741","lieuActuel":"2.3015208747198264,48.871264817483635","gravite":"UA","id":"10","hopital":{"uuid":"35","name":"Hôpital Saint-Louis","location":"48.8732305,2.3698087000000214","reveil":{"nombreLitsDisponibles":4,"nombrePatientsEnRoute":0,"nombreLitsOccupes":0,"tension":"Vert"},"urgence":{"nombreLitsDisponibles":9,"nombrePatientsEnRoute":0,"nombreLitsOccupes":0,"tension":"Rouge"},"active":"true"},"hopitalUUID":"35","description":"Description eba7bb57-e129-4258-8900-5ed7598c266c","photos":""}""")).asJSON)

	setUp(
		scn.inject(
			constantUsersPerSec(1) during(1 minute)
		)).protocols(httpProtocol)
}

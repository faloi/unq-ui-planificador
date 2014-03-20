package uqbar.scala.ejemplos.scala.conversor.modelo

import org.uqbar.commons.utils.Observable

/**
 * Esta clase realiza la conversion de millas a kilometros
 */

@Observable
class Conversor {
	var _country = 0.0
	var millas = 0.0
	var kilometros = 0.0
	val factor_conversion_millas_kilometros = 1.609344
	
	def convertir() = {kilometros = millas * factor_conversion_millas_kilometros; country = kilometros }
	
	
	def country_=(country:Double) {
		this._country = country;
	}
	
	def country = this._country
}
package edu.unq.uis.planificador.ui.filters

class DateTextFilter extends TextFilter {
override accept(TextInputEvent event) {
val expected = new ArrayList(#["\\d", "\\d?", "/", "\\d", "\\d?", "/", "\\d{0,4}"])
val regex = expected.reverse.fold("")[result, element| '''(Â«elementÂ»Â«resultÂ»)?''']
event.potentialTextResult.matches(regex)
}
}

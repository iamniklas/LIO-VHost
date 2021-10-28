package led

class Area {
    var startIndex: Int
    var endIndex: Int

    constructor(_start: Int, _end: Int) {
        startIndex = _start
        endIndex = _end
    }

    constructor(_area: Area) {
        startIndex = _area.startIndex
        endIndex = _area.endIndex
    }

    fun redefineArea(_start: Int, _end: Int) {
        startIndex = _start
        endIndex = _end
    }
}
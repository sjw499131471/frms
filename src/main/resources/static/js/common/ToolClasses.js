function Set() {
    let items = {}
    this.has = function (value) {
        return value in items
    }
    this.add = function (value) {
        if (!this.has(value)) {
            items[value] = value
            return true
        }
        return false
    }
    this.remove = function (value) {
        if (this.has(value)) {
            delete items[value]
            return true
        }
        return false
    }
    this.clear = function () {
        items = []
    }
    this.size = function () {
        return Object.keys(items).length
    }
    this.values = function () { // 提取items对象的所有属性，以数组形式返回
        return Object.keys(items)
    }
}
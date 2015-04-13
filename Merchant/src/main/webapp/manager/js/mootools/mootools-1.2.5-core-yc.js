// MooTools, <http://mootools.net>, My Object Oriented (JavaScript) Tools.
// Copyright (c) 2006-2009 Valerio Proietti, <http://mad4milk.net>, MIT Style
// License.

var MooTools = {
	version : "1.2.5",
	build : "008d8f0f2fcc2044e54fdd3635341aaab274e757"
};
var Native = function(l) {
	l = l || {};
	var a = l.name;
	var j = l.legacy;
	var b = l.protect;
	var c = l.implement;
	var i = l.generics;
	var g = l.initialize;
	var h = l.afterImplement || function() {
	};
	var d = g || j;
	i = i !== false;
	d.constructor = Native;
	d.$family = {
		name : "native"
	};
	if (j && g) {
		d.prototype = j.prototype;
	}
	d.prototype.constructor = d;
	if (a) {
		var f = a.toLowerCase();
		d.prototype.$family = {
			name : f
		};
		Native.typize(d, f);
	}
	var k = function(o, m, p, n) {
		if (!b || n || !o.prototype[m]) {
			o.prototype[m] = p;
		}
		if (i) {
			Native.genericize(o, m, b);
		}
		h.call(o, m, p);
		return o;
	};
	d.alias = function(o, m, q) {
		if (typeof o == "string") {
			var p = this.prototype[o];
			if ((o = p)) {
				return k(this, m, o, q);
			}
		}
		for (var n in o) {
			this.alias(n, o[n], m);
		}
		return this;
	};
	d.implement = function(n, m, q) {
		if (typeof n == "string") {
			return k(this, n, m, q);
		}
		for (var o in n) {
			k(this, o, n[o], m);
		}
		return this;
	};
	if (c) {
		d.implement(c);
	}
	return d;
};
Native.genericize = function(b, c, a) {
	if ((!a || !b[c]) && typeof b.prototype[c] == "function") {
		b[c] = function() {
			var d = Array.prototype.slice.call(arguments);
			return b.prototype[c].apply(d.shift(), d);
		};
	}
};
Native.implement = function(d, c) {
	for (var b = 0, a = d.length; b < a; b++) {
		d[b].implement(c);
	}
};
Native.typize = function(a, b) {
	if (!a.type) {
		a.type = function(c) {
			return ($type(c) === b);
		};
	}
};
(function() {
	var a = {
		Array : Array,
		Date : Date,
		Function : Function,
		Number : Number,
		RegExp : RegExp,
		String : String
	};
	for (var j in a) {
		new Native({
					name : j,
					initialize : a[j],
					protect : true
				});
	}
	var d = {
		"boolean" : Boolean,
		"native" : Native,
		object : Object
	};
	for (var c in d) {
		Native.typize(d[c], c);
	}
	var h = {
		Array : ["concat", "indexOf", "join", "lastIndexOf", "pop", "push",
				"reverse", "shift", "slice", "sort", "splice", "toString",
				"unshift", "valueOf"],
		String : ["charAt", "charCodeAt", "concat", "indexOf", "lastIndexOf",
				"match", "replace", "search", "slice", "split", "substr",
				"substring", "toLowerCase", "toUpperCase", "valueOf"]
	};
	for (var f in h) {
		for (var b = h[f].length; b--;) {
			Native.genericize(a[f], h[f][b], true);
		}
	}
})();
var Hash = new Native({
			name : "Hash",
			initialize : function(a) {
				if ($type(a) == "hash") {
					a = $unlink(a.getClean());
				}
				for (var b in a) {
					this[b] = a[b];
				}
				return this;
			}
		});
Hash.implement({
			forEach : function(b, c) {
				for (var a in this) {
					if (this.hasOwnProperty(a)) {
						b.call(c, this[a], a, this);
					}
				}
			},
			getClean : function() {
				var b = {};
				for (var a in this) {
					if (this.hasOwnProperty(a)) {
						b[a] = this[a];
					}
				}
				return b;
			},
			getLength : function() {
				var b = 0;
				for (var a in this) {
					if (this.hasOwnProperty(a)) {
						b++;
					}
				}
				return b;
			}
		});
Hash.alias("forEach", "each");
Array.implement({
			forEach : function(c, d) {
				for (var b = 0, a = this.length; b < a; b++) {
					c.call(d, this[b], b, this);
				}
			}
		});
Array.alias("forEach", "each");
function $A(b) {
	if (b.item) {
		var a = b.length, c = new Array(a);
		while (a--) {
			c[a] = b[a];
		}
		return c;
	}
	return Array.prototype.slice.call(b);
}
function $arguments(a) {
	return function() {
		return arguments[a];
	};
}
function $chk(a) {
	return !!(a || a === 0);
}
function $clear(a) {
	clearTimeout(a);
	clearInterval(a);
	return null;
}
function $defined(a) {
	return (a != undefined);
}
function $each(c, b, d) {
	var a = $type(c);
	((a == "arguments" || a == "collection" || a == "array") ? Array : Hash)
			.each(c, b, d);
}
function $empty() {
}
function $extend(c, a) {
	for (var b in (a || {})) {
		c[b] = a[b];
	}
	return c;
}
function $H(a) {
	return new Hash(a);
}
function $lambda(a) {
	return ($type(a) == "function") ? a : function() {
		return a;
	};
}
function $merge() {
	var a = Array.slice(arguments);
	a.unshift({});
	return $mixin.apply(null, a);
}
function $mixin(f) {
	for (var d = 1, a = arguments.length; d < a; d++) {
		var b = arguments[d];
		if ($type(b) != "object") {
			continue;
		}
		for (var c in b) {
			var h = b[c], g = f[c];
			f[c] = (g && $type(h) == "object" && $type(g) == "object")
					? $mixin(g, h)
					: $unlink(h);
		}
	}
	return f;
}
function $pick() {
	for (var b = 0, a = arguments.length; b < a; b++) {
		if (arguments[b] != undefined) {
			return arguments[b];
		}
	}
	return null;
}
function $random(b, a) {
	return Math.floor(Math.random() * (a - b + 1) + b);
}
function $splat(b) {
	var a = $type(b);
	return (a) ? ((a != "array" && a != "arguments") ? [b] : b) : [];
}
var $time = Date.now || function() {
	return +new Date;
};
function $try() {
	for (var b = 0, a = arguments.length; b < a; b++) {
		try {
			return arguments[b]();
		} catch (c) {
		}
	}
	return null;
}
function $type(a) {
	if (a == undefined) {
		return false;
	}
	if (a.$family) {
		return (a.$family.name == "number" && !isFinite(a))
				? false
				: a.$family.name;
	}
	if (a.nodeName) {
		switch (a.nodeType) {
			case 1 :
				return "element";
			case 3 :
				return (/\S/).test(a.nodeValue) ? "textnode" : "whitespace";
		}
	} else {
		if (typeof a.length == "number") {
			if (a.callee) {
				return "arguments";
			} else {
				if (a.item) {
					return "collection";
				}
			}
		}
	}
	return typeof a;
}
function $unlink(c) {
	var b;
	switch ($type(c)) {
		case "object" :
			b = {};
			for (var f in c) {
				b[f] = $unlink(c[f]);
			}
			break;
		case "hash" :
			b = new Hash(c);
			break;
		case "array" :
			b = [];
			for (var d = 0, a = c.length; d < a; d++) {
				b[d] = $unlink(c[d]);
			}
			break;
		default :
			return c;
	}
	return b;
}
Array.implement({
	every : function(c, d) {
		for (var b = 0, a = this.length; b < a; b++) {
			if (!c.call(d, this[b], b, this)) {
				return false;
			}
		}
		return true;
	},
	filter : function(d, f) {
		var c = [];
		for (var b = 0, a = this.length; b < a; b++) {
			if (d.call(f, this[b], b, this)) {
				c.push(this[b]);
			}
		}
		return c;
	},
	clean : function() {
		return this.filter($defined);
	},
	indexOf : function(c, d) {
		var a = this.length;
		for (var b = (d < 0) ? Math.max(0, a + d) : d || 0; b < a; b++) {
			if (this[b] === c) {
				return b;
			}
		}
		return -1;
	},
	map : function(d, f) {
		var c = [];
		for (var b = 0, a = this.length; b < a; b++) {
			c[b] = d.call(f, this[b], b, this);
		}
		return c;
	},
	some : function(c, d) {
		for (var b = 0, a = this.length; b < a; b++) {
			if (c.call(d, this[b], b, this)) {
				return true;
			}
		}
		return false;
	},
	associate : function(c) {
		var d = {}, b = Math.min(this.length, c.length);
		for (var a = 0; a < b; a++) {
			d[c[a]] = this[a];
		}
		return d;
	},
	link : function(c) {
		var a = {};
		for (var f = 0, b = this.length; f < b; f++) {
			for (var d in c) {
				if (c[d](this[f])) {
					a[d] = this[f];
					delete c[d];
					break;
				}
			}
		}
		return a;
	},
	contains : function(a, b) {
		return this.indexOf(a, b) != -1;
	},
	extend : function(c) {
		for (var b = 0, a = c.length; b < a; b++) {
			this.push(c[b]);
		}
		return this;
	},
	getLast : function() {
		return (this.length) ? this[this.length - 1] : null;
	},
	getRandom : function() {
		return (this.length) ? this[$random(0, this.length - 1)] : null;
	},
	include : function(a) {
		if (!this.contains(a)) {
			this.push(a);
		}
		return this;
	},
	combine : function(c) {
		for (var b = 0, a = c.length; b < a; b++) {
			this.include(c[b]);
		}
		return this;
	},
	erase : function(b) {
		for (var a = this.length; a--; a) {
			if (this[a] === b) {
				this.splice(a, 1);
			}
		}
		return this;
	},
	empty : function() {
		this.length = 0;
		return this;
	},
	flatten : function() {
		var d = [];
		for (var b = 0, a = this.length; b < a; b++) {
			var c = $type(this[b]);
			if (!c) {
				continue;
			}
			d = d
					.concat((c == "array" || c == "collection" || c == "arguments")
							? Array.flatten(this[b])
							: this[b]);
		}
		return d;
	},
	hexToRgb : function(b) {
		if (this.length != 3) {
			return null;
		}
		var a = this.map(function(c) {
					if (c.length == 1) {
						c += c;
					}
					return c.toInt(16);
				});
		return (b) ? a : "rgb(" + a + ")";
	},
	rgbToHex : function(d) {
		if (this.length < 3) {
			return null;
		}
		if (this.length == 4 && this[3] == 0 && !d) {
			return "transparent";
		}
		var b = [];
		for (var a = 0; a < 3; a++) {
			var c = (this[a] - 0).toString(16);
			b.push((c.length == 1) ? "0" + c : c);
		}
		return (d) ? b : "#" + b.join("");
	}
});
String.implement({
			test : function(a, b) {
				return ((typeof a == "string") ? new RegExp(a, b) : a)
						.test(this);
			},
			contains : function(a, b) {
				return (b) ? (b + this + b).indexOf(b + a + b) > -1 : this
						.indexOf(a) > -1;
			},
			trim : function() {
				return this.replace(/^\s+|\s+$/g, "");
			},
			clean : function() {
				return this.replace(/\s+/g, " ").trim();
			},
			camelCase : function() {
				return this.replace(/-\D/g, function(a) {
							return a.charAt(1).toUpperCase();
						});
			},
			hyphenate : function() {
				return this.replace(/[A-Z]/g, function(a) {
							return ("-" + a.charAt(0).toLowerCase());
						});
			},
			capitalize : function() {
				return this.replace(/\b[a-z]/g, function(a) {
							return a.toUpperCase();
						});
			},
			escapeRegExp : function() {
				return this.replace(/([-.*+?^${}()|[\]\/\\])/g, "\\$1");
			},
			toInt : function(a) {
				return parseInt(this, a || 10);
			},
			toFloat : function() {
				return parseFloat(this);
			},
			hexToRgb : function(b) {
				var a = this.match(/^#?(\w{1,2})(\w{1,2})(\w{1,2})$/);
				return (a) ? a.slice(1).hexToRgb(b) : null;
			},
			rgbToHex : function(b) {
				var a = this.match(/\d{1,3}/g);
				return (a) ? a.rgbToHex(b) : null;
			},
			stripScripts : function(b) {
				var a = "";
				var c = this.replace(/<script[^>]*>([\s\S]*?)<\/script>/gi,
						function() {
							a += arguments[1] + "\n";
							return "";
						});
				if (b === true) {
					$exec(a);
				} else {
					if ($type(b) == "function") {
						b(a, c);
					}
				}
				return c;
			},
			substitute : function(a, b) {
				return this.replace(b || (/\\?\{([^{}]+)\}/g), function(d, c) {
							if (d.charAt(0) == "\\") {
								return d.slice(1);
							}
							return (a[c] != undefined) ? a[c] : "";
						});
			}
		});
try {
	delete Function.prototype.bind;
} catch (e) {
}
Function.implement({
			extend : function(a) {
				for (var b in a) {
					this[b] = a[b];
				}
				return this;
			},
			create : function(b) {
				var a = this;
				b = b || {};
				return function(d) {
					var c = b.arguments;
					c = (c != undefined) ? $splat(c) : Array.slice(arguments,
							(b.event) ? 1 : 0);
					if (b.event) {
						c = [d || window.event].extend(c);
					}
					var f = function() {
						return a.apply(b.bind || null, c);
					};
					if (b.delay) {
						return setTimeout(f, b.delay);
					}
					if (b.periodical) {
						return setInterval(f, b.periodical);
					}
					if (b.attempt) {
						return $try(f);
					}
					return f();
				};
			},
			run : function(a, b) {
				return this.apply(b, $splat(a));
			},
			pass : function(a, b) {
				return this.create({
							bind : b,
							arguments : a
						});
			},
			bind : function(b, a) {
				return this.create({
							bind : b,
							arguments : a
						});
			},
			bindWithEvent : function(b, a) {
				return this.create({
							bind : b,
							arguments : a,
							event : true
						});
			},
			attempt : function(a, b) {
				return this.create({
							bind : b,
							arguments : a,
							attempt : true
						})();
			},
			delay : function(b, c, a) {
				return this.create({
							bind : c,
							arguments : a,
							delay : b
						})();
			},
			periodical : function(c, b, a) {
				return this.create({
							bind : b,
							arguments : a,
							periodical : c
						})();
			}
		});
Number.implement({
			limit : function(b, a) {
				return Math.min(a, Math.max(b, this));
			},
			round : function(a) {
				a = Math.pow(10, a || 0);
				return Math.round(this * a) / a;
			},
			times : function(b, c) {
				for (var a = 0; a < this; a++) {
					b.call(c, a, this);
				}
			},
			toFloat : function() {
				return parseFloat(this);
			},
			toInt : function(a) {
				return parseInt(this, a || 10);
			}
		});
Number.alias("times", "each");
(function(b) {
	var a = {};
	b.each(function(c) {
				if (!Number[c]) {
					a[c] = function() {
						return Math[c]
								.apply(null, [this].concat($A(arguments)));
					};
				}
			});
	Number.implement(a);
})(["abs", "acos", "asin", "atan", "atan2", "ceil", "cos", "exp", "floor",
		"log", "max", "min", "pow", "sin", "sqrt", "tan"]);
Hash.implement({
			has : Object.prototype.hasOwnProperty,
			keyOf : function(b) {
				for (var a in this) {
					if (this.hasOwnProperty(a) && this[a] === b) {
						return a;
					}
				}
				return null;
			},
			hasValue : function(a) {
				return (Hash.keyOf(this, a) !== null);
			},
			extend : function(a) {
				Hash.each(a || {}, function(c, b) {
							Hash.set(this, b, c);
						}, this);
				return this;
			},
			combine : function(a) {
				Hash.each(a || {}, function(c, b) {
							Hash.include(this, b, c);
						}, this);
				return this;
			},
			erase : function(a) {
				if (this.hasOwnProperty(a)) {
					delete this[a];
				}
				return this;
			},
			get : function(a) {
				return (this.hasOwnProperty(a)) ? this[a] : null;
			},
			set : function(a, b) {
				if (!this[a] || this.hasOwnProperty(a)) {
					this[a] = b;
				}
				return this;
			},
			empty : function() {
				Hash.each(this, function(b, a) {
							delete this[a];
						}, this);
				return this;
			},
			include : function(a, b) {
				if (this[a] == undefined) {
					this[a] = b;
				}
				return this;
			},
			map : function(b, c) {
				var a = new Hash;
				Hash.each(this, function(f, d) {
							a.set(d, b.call(c, f, d, this));
						}, this);
				return a;
			},
			filter : function(b, c) {
				var a = new Hash;
				Hash.each(this, function(f, d) {
							if (b.call(c, f, d, this)) {
								a.set(d, f);
							}
						}, this);
				return a;
			},
			every : function(b, c) {
				for (var a in this) {
					if (this.hasOwnProperty(a) && !b.call(c, this[a], a)) {
						return false;
					}
				}
				return true;
			},
			some : function(b, c) {
				for (var a in this) {
					if (this.hasOwnProperty(a) && b.call(c, this[a], a)) {
						return true;
					}
				}
				return false;
			},
			getKeys : function() {
				var a = [];
				Hash.each(this, function(c, b) {
							a.push(b);
						});
				return a;
			},
			getValues : function() {
				var a = [];
				Hash.each(this, function(b) {
							a.push(b);
						});
				return a;
			},
			toQueryString : function(a) {
				var b = [];
				Hash.each(this, function(g, f) {
							if (a) {
								f = a + "[" + f + "]";
							}
							var d;
							switch ($type(g)) {
								case "object" :
									d = Hash.toQueryString(g, f);
									break;
								case "array" :
									var c = {};
									g.each(function(j, h) {
												c[h] = j;
											});
									d = Hash.toQueryString(c, f);
									break;
								default :
									d = f + "=" + encodeURIComponent(g);
							}
							if (g != undefined) {
								b.push(d);
							}
						});
				return b.join("&");
			}
		});
Hash.alias({
			keyOf : "indexOf",
			hasValue : "contains"
		});
function Class(b) {
	if (b instanceof Function) {
		b = {
			initialize : b
		};
	}
	var a = function() {
		Object.reset(this);
		if (a._prototyping) {
			return this;
		}
		this._current = $empty;
		var c = (this.initialize)
				? this.initialize.apply(this, arguments)
				: this;
		delete this._current;
		delete this.caller;
		return c;
	}.extend(this);
	a.implement(b);
	a.constructor = Class;
	a.prototype.constructor = a;
	return a;
}
Function.prototype.protect = function() {
	this._protected = true;
	return this;
};
Object.reset = function(a, c) {
	if (c == null) {
		for (var f in a) {
			Object.reset(a, f);
		}
		return a;
	}
	delete a[c];
	switch ($type(a[c])) {
		case "object" :
			var d = function() {
			};
			d.prototype = a[c];
			var b = new d;
			a[c] = Object.reset(b);
			break;
		case "array" :
			a[c] = $unlink(a[c]);
			break;
	}
	return a;
};
new Native({
			name : "Class",
			initialize : Class
		}).extend({
			instantiate : function(b) {
				b._prototyping = true;
				var a = new b;
				delete b._prototyping;
				return a;
			},
			wrap : function(a, b, c) {
				if (c._origin) {
					c = c._origin;
				}
				return function() {
					if (c._protected && this._current == null) {
						throw new Error('The method "' + b
								+ '" cannot be called.');
					}
					var f = this.caller, g = this._current;
					this.caller = g;
					this._current = arguments.callee;
					var d = c.apply(this, arguments);
					this._current = g;
					this.caller = f;
					return d;
				}.extend({
							_owner : a,
							_origin : c,
							_name : b
						});
			}
		});
Class.implement({
			implement : function(a, d) {
				if ($type(a) == "object") {
					for (var f in a) {
						this.implement(f, a[f]);
					}
					return this;
				}
				var g = Class.Mutators[a];
				if (g) {
					d = g.call(this, d);
					if (d == null) {
						return this;
					}
				}
				var c = this.prototype;
				switch ($type(d)) {
					case "function" :
						if (d._hidden) {
							return this;
						}
						c[a] = Class.wrap(this, a, d);
						break;
					case "object" :
						var b = c[a];
						if ($type(b) == "object") {
							$mixin(b, d);
						} else {
							c[a] = $unlink(d);
						}
						break;
					case "array" :
						c[a] = $unlink(d);
						break;
					default :
						c[a] = d;
				}
				return this;
			}
		});
Class.Mutators = {
	Extends : function(a) {
		this.parent = a;
		this.prototype = Class.instantiate(a);
		this.implement("parent", function() {
			var b = this.caller._name, c = this.caller._owner.parent.prototype[b];
			if (!c) {
				throw new Error('The method "' + b + '" has no parent.');
			}
			return c.apply(this, arguments);
		}.protect());
	},
	Implements : function(a) {
		$splat(a).each(function(b) {
					if (b instanceof Function) {
						b = Class.instantiate(b);
					}
					this.implement(b);
				}, this);
	}
};
var Chain = new Class({
			$chain : [],
			chain : function() {
				this.$chain.extend(Array.flatten(arguments));
				return this;
			},
			callChain : function() {
				return (this.$chain.length) ? this.$chain.shift().apply(this,
						arguments) : false;
			},
			clearChain : function() {
				this.$chain.empty();
				return this;
			}
		});
var Events = new Class({
			$events : {},
			addEvent : function(c, b, a) {
				c = Events.removeOn(c);
				if (b != $empty) {
					this.$events[c] = this.$events[c] || [];
					this.$events[c].include(b);
					if (a) {
						b.internal = true;
					}
				}
				return this;
			},
			addEvents : function(a) {
				for (var b in a) {
					this.addEvent(b, a[b]);
				}
				return this;
			},
			fireEvent : function(c, b, a) {
				c = Events.removeOn(c);
				if (!this.$events || !this.$events[c]) {
					return this;
				}
				this.$events[c].each(function(d) {
							d.create({
										bind : this,
										delay : a,
										"arguments" : b
									})();
						}, this);
				return this;
			},
			removeEvent : function(b, a) {
				b = Events.removeOn(b);
				if (!this.$events[b]) {
					return this;
				}
				if (!a.internal) {
					this.$events[b].erase(a);
				}
				return this;
			},
			removeEvents : function(c) {
				var d;
				if ($type(c) == "object") {
					for (d in c) {
						this.removeEvent(d, c[d]);
					}
					return this;
				}
				if (c) {
					c = Events.removeOn(c);
				}
				for (d in this.$events) {
					if (c && c != d) {
						continue;
					}
					var b = this.$events[d];
					for (var a = b.length; a--; a) {
						this.removeEvent(d, b[a]);
					}
				}
				return this;
			}
		});
Events.removeOn = function(a) {
	return a.replace(/^on([A-Z])/, function(b, c) {
				return c.toLowerCase();
			});
};
var Options = new Class({
			setOptions : function() {
				this.options = $merge.run([this.options].extend(arguments));
				if (!this.addEvent) {
					return this;
				}
				for (var a in this.options) {
					if ($type(this.options[a]) != "function"
							|| !(/^on[A-Z]/).test(a)) {
						continue;
					}
					this.addEvent(a, this.options[a]);
					delete this.options[a];
				}
				return this;
			}
		});
var Browser = $merge({
	Engine : {
		name : "unknown",
		version : 0
	},
	Platform : {
		name : (window.orientation != undefined) ? "ipod" : (navigator.platform
				.match(/mac|win|linux/i) || ["other"])[0].toLowerCase()
	},
	Features : {
		xpath : !!(document.evaluate),
		air : !!(window.runtime),
		query : !!(document.querySelector)
	},
	Plugins : {},
	Engines : {
		presto : function() {
			return (!window.opera) ? false : ((arguments.callee.caller)
					? 960
					: ((document.getElementsByClassName) ? 950 : 925));
		},
		trident : function() {
			return (!window.ActiveXObject) ? false : ((window.XMLHttpRequest)
					? ((document.querySelectorAll) ? 6 : 5)
					: 4);
		},
		webkit : function() {
			return (navigator.taintEnabled) ? false : ((Browser.Features.xpath)
					? ((Browser.Features.query) ? 525 : 420)
					: 419);
		},
		gecko : function() {
			return (!document.getBoxObjectFor && window.mozInnerScreenX == null)
					? false
					: ((document.getElementsByClassName) ? 19 : 18);
		}
	}
}, Browser || {});
Browser.Platform[Browser.Platform.name] = true;
Browser.detect = function() {
	for (var b in this.Engines) {
		var a = this.Engines[b]();
		if (a) {
			this.Engine = {
				name : b,
				version : a
			};
			this.Engine[b] = this.Engine[b + a] = true;
			break;
		}
	}
	return {
		name : b,
		version : a
	};
};
Browser.detect();
Browser.Request = function() {
	return $try(function() {
				return new XMLHttpRequest();
			}, function() {
				return new ActiveXObject("MSXML2.XMLHTTP");
			}, function() {
				return new ActiveXObject("Microsoft.XMLHTTP");
			});
};
Browser.Features.xhr = !!(Browser.Request());
Browser.Plugins.Flash = (function() {
	var a = ($try(function() {
				return navigator.plugins["Shockwave Flash"].description;
			}, function() {
				return new ActiveXObject("ShockwaveFlash.ShockwaveFlash")
						.GetVariable("$version");
			}) || "0 r0").match(/\d+/g);
	return {
		version : parseInt(a[0] || 0 + "." + a[1], 10) || 0,
		build : parseInt(a[2], 10) || 0
	};
})();
function $exec(b) {
	if (!b) {
		return b;
	}
	if (window.execScript) {
		window.execScript(b);
	} else {
		var a = document.createElement("script");
		a.setAttribute("type", "text/javascript");
		a[(Browser.Engine.webkit && Browser.Engine.version < 420)
				? "innerText"
				: "text"] = b;
		document.head.appendChild(a);
		document.head.removeChild(a);
	}
	return b;
}
Native.UID = 1;
var $uid = (Browser.Engine.trident) ? function(a) {
	return (a.uid || (a.uid = [Native.UID++]))[0];
} : function(a) {
	return a.uid || (a.uid = Native.UID++);
};
var Window = new Native({
			name : "Window",
			legacy : (Browser.Engine.trident) ? null : window.Window,
			initialize : function(a) {
				$uid(a);
				if (!a.Element) {
					a.Element = $empty;
					if (Browser.Engine.webkit) {
						a.document.createElement("iframe");
					}
					a.Element.prototype = (Browser.Engine.webkit)
							? window["[[DOMElement.prototype]]"]
							: {};
				}
				a.document.window = a;
				return $extend(a, Window.Prototype);
			},
			afterImplement : function(b, a) {
				window[b] = Window.Prototype[b] = a;
			}
		});
Window.Prototype = {
	$family : {
		name : "window"
	}
};
new Window(window);
var Document = new Native({
			name : "Document",
			legacy : (Browser.Engine.trident) ? null : window.Document,
			initialize : function(a) {
				$uid(a);
				a.head = a.getElementsByTagName("head")[0];
				a.html = a.getElementsByTagName("html")[0];
				if (Browser.Engine.trident && Browser.Engine.version <= 4) {
					$try(function() {
								a.execCommand("BackgroundImageCache", false,
										true);
							});
				}
				if (Browser.Engine.trident) {
					a.window.attachEvent("onunload", function() {
								a.window.detachEvent("onunload",
										arguments.callee);
								a.head = a.html = a.window = null;
							});
				}
				return $extend(a, Document.Prototype);
			},
			afterImplement : function(b, a) {
				document[b] = Document.Prototype[b] = a;
			}
		});
Document.Prototype = {
	$family : {
		name : "document"
	}
};
new Document(document);
var Element = new Native({
			name : "Element",
			legacy : window.Element,
			initialize : function(a, b) {
				var c = Element.Constructors.get(a);
				if (c) {
					return c(b);
				}
				if (typeof a == "string") {
					return document.newElement(a, b);
				}
				return document.id(a).set(b);
			},
			afterImplement : function(a, b) {
				Element.Prototype[a] = b;
				if (Array[a]) {
					return;
				}
				Elements.implement(a, function() {
							var c = [], h = true;
							for (var f = 0, d = this.length; f < d; f++) {
								var g = this[f][a].apply(this[f], arguments);
								c.push(g);
								if (h) {
									h = ($type(g) == "element");
								}
							}
							return (h) ? new Elements(c) : c;
						});
			}
		});
Element.Prototype = {
	$family : {
		name : "element"
	}
};
Element.Constructors = new Hash;
var IFrame = new Native({
			name : "IFrame",
			generics : false,
			initialize : function() {
				var g = Array.link(arguments, {
							properties : Object.type,
							iframe : $defined
						});
				var d = g.properties || {};
				var c = document.id(g.iframe);
				var f = d.onload || $empty;
				delete d.onload;
				d.id = d.name = $pick(d.id, d.name, c
								? (c.id || c.name)
								: "IFrame_" + $time());
				c = new Element(c || "iframe", d);
				var b = function() {
					var h = $try(function() {
								return c.contentWindow.location.host;
							});
					if (!h || h == window.location.host) {
						var i = new Window(c.contentWindow);
						new Document(c.contentWindow.document);
						$extend(i.Element.prototype, Element.Prototype);
					}
					f.call(c.contentWindow, c.contentWindow.document);
				};
				var a = $try(function() {
							return c.contentWindow;
						});
				((a && a.document.body) || window.frames[d.id]) ? b() : c
						.addListener("load", b);
				return c;
			}
		});
var Elements = new Native({
			initialize : function(g, b) {
				b = $extend({
							ddup : true,
							cash : true
						}, b);
				g = g || [];
				if (b.ddup || b.cash) {
					var h = {}, f = [];
					for (var c = 0, a = g.length; c < a; c++) {
						var d = document.id(g[c], !b.cash);
						if (b.ddup) {
							if (h[d.uid]) {
								continue;
							}
							h[d.uid] = true;
						}
						if (d) {
							f.push(d);
						}
					}
					g = f;
				}
				return (b.cash) ? $extend(g, this) : g;
			}
		});
Elements.implement({
			filter : function(a, b) {
				if (!a) {
					return this;
				}
				return new Elements(Array.filter(this, (typeof a == "string")
								? function(c) {
									return c.match(a);
								}
								: a, b));
			}
		});
(function() {
	var d;
	try {
		var a = document.createElement("<input name=x>");
		d = (a.name == "x");
	} catch (b) {
	}
	var c = function(f) {
		return ("" + f).replace(/&/g, "&amp;").replace(/"/g, "&quot;");
	};
	Document.implement({
				newElement : function(f, g) {
					if (g && g.checked != null) {
						g.defaultChecked = g.checked;
					}
					if (d && g) {
						f = "<" + f;
						if (g.name) {
							f += ' name="' + c(g.name) + '"';
						}
						if (g.type) {
							f += ' type="' + c(g.type) + '"';
						}
						f += ">";
						delete g.name;
						delete g.type;
					}
					return this.id(this.createElement(f)).set(g);
				},
				newTextNode : function(f) {
					return this.createTextNode(f);
				},
				getDocument : function() {
					return this;
				},
				getWindow : function() {
					return this.window;
				},
				id : (function() {
					var f = {
						string : function(i, h, g) {
							i = g.getElementById(i);
							return (i) ? f.element(i, h) : null;
						},
						element : function(g, j) {
							$uid(g);
							if (!j && !g.$family
									&& !(/^object|embed$/i).test(g.tagName)) {
								var h = Element.Prototype;
								for (var i in h) {
									g[i] = h[i];
								}
							}
							return g;
						},
						object : function(h, i, g) {
							if (h.toElement) {
								return f.element(h.toElement(g), i);
							}
							return null;
						}
					};
					f.textnode = f.whitespace = f.window = f.document = $arguments(0);
					return function(h, j, i) {
						if (h && h.$family && h.uid) {
							return h;
						}
						var g = $type(h);
						return (f[g]) ? f[g](h, j, i || document) : null;
					};
				})()
			});
})();
if (window.$ == null) {
	Window.implement({
				$ : function(a, b) {
					return document.id(a, b, this.document);
				}
			});
}
Window.implement({
			$$ : function(a) {
				if (arguments.length == 1 && typeof a == "string") {
					return this.document.getElements(a);
				}
				var g = [];
				var c = Array.flatten(arguments);
				for (var d = 0, b = c.length; d < b; d++) {
					var f = c[d];
					switch ($type(f)) {
						case "element" :
							g.push(f);
							break;
						case "string" :
							g.extend(this.document.getElements(f, true));
					}
				}
				return new Elements(g);
			},
			getDocument : function() {
				return this.document;
			},
			getWindow : function() {
				return this;
			}
		});
Native.implement([Element, Document], {
			getElement : function(a, b) {
				return document.id(this.getElements(a, true)[0] || null, b);
			},
			getElements : function(a, d) {
				a = a.split(",");
				var c = [];
				var b = (a.length > 1);
				a.each(function(f) {
							var g = this.getElementsByTagName(f.trim());
							(b) ? c.extend(g) : c = g;
						}, this);
				return new Elements(c, {
							ddup : b,
							cash : !d
						});
			}
		});
(function() {
	var i = {}, g = {};
	var j = {
		input : "checked",
		option : "selected",
		textarea : (Browser.Engine.webkit && Browser.Engine.version < 420)
				? "innerHTML"
				: "value"
	};
	var c = function(m) {
		return (g[m] || (g[m] = {}));
	};
	var h = function(o, m) {
		if (!o) {
			return;
		}
		var n = o.uid;
		if (m !== true) {
			m = false;
		}
		if (Browser.Engine.trident) {
			if (o.clearAttributes) {
				var r = m && o.cloneNode(false);
				o.clearAttributes();
				if (r) {
					o.mergeAttributes(r);
				}
			} else {
				if (o.removeEvents) {
					o.removeEvents();
				}
			}
			if ((/object/i).test(o.tagName)) {
				for (var q in o) {
					if (typeof o[q] == "function") {
						o[q] = $empty;
					}
				}
				Element.dispose(o);
			}
		}
		if (!n) {
			return;
		}
		i[n] = g[n] = null;
	};
	var d = function() {
		Hash.each(i, h);
		if (Browser.Engine.trident) {
			$A(document.getElementsByTagName("object")).each(h);
		}
		if (window.CollectGarbage) {
			CollectGarbage();
		}
		i = g = null;
	};
	var k = function(o, m, t, n, q, s) {
		var p = o[t || m];
		var r = [];
		while (p) {
			if (p.nodeType == 1 && (!n || Element.match(p, n))) {
				if (!q) {
					return document.id(p, s);
				}
				r.push(p);
			}
			p = p[m];
		}
		return (q) ? new Elements(r, {
					ddup : false,
					cash : !s
				}) : null;
	};
	var f = {
		html : "innerHTML",
		"class" : "className",
		"for" : "htmlFor",
		defaultValue : "defaultValue",
		text : (Browser.Engine.trident || (Browser.Engine.webkit && Browser.Engine.version < 420))
				? "innerText"
				: "textContent"
	};
	var b = ["compact", "nowrap", "ismap", "declare", "noshade", "checked",
			"disabled", "readonly", "multiple", "selected", "noresize", "defer"];
	var l = ["value", "type", "defaultValue", "accessKey", "cellPadding",
			"cellSpacing", "colSpan", "frameBorder", "maxLength", "readOnly",
			"rowSpan", "tabIndex", "useMap"];
	b = b.associate(b);
	Hash.extend(f, b);
	Hash.extend(f, l.associate(l.map(String.toLowerCase)));
	var a = {
		before : function(n, m) {
			if (m.parentNode) {
				m.parentNode.insertBefore(n, m);
			}
		},
		after : function(n, m) {
			if (!m.parentNode) {
				return;
			}
			var o = m.nextSibling;
			(o) ? m.parentNode.insertBefore(n, o) : m.parentNode.appendChild(n);
		},
		bottom : function(n, m) {
			m.appendChild(n);
		},
		top : function(n, m) {
			var o = m.firstChild;
			(o) ? m.insertBefore(n, o) : m.appendChild(n);
		}
	};
	a.inside = a.bottom;
	Hash.each(a, function(m, n) {
				n = n.capitalize();
				Element.implement("inject" + n, function(o) {
							m(this, document.id(o, true));
							return this;
						});
				Element.implement("grab" + n, function(o) {
							m(document.id(o, true), this);
							return this;
						});
			});
	Element.implement({
		set : function(q, n) {
			switch ($type(q)) {
				case "object" :
					for (var o in q) {
						this.set(o, q[o]);
					}
					break;
				case "string" :
					var m = Element.Properties.get(q);
					(m && m.set)
							? m.set.apply(this, Array.slice(arguments, 1))
							: this.setProperty(q, n);
			}
			return this;
		},
		get : function(n) {
			var m = Element.Properties.get(n);
			return (m && m.get)
					? m.get.apply(this, Array.slice(arguments, 1))
					: this.getProperty(n);
		},
		erase : function(n) {
			var m = Element.Properties.get(n);
			(m && m.erase) ? m.erase.apply(this) : this.removeProperty(n);
			return this;
		},
		setProperty : function(n, o) {
			var m = f[n];
			if (o == undefined) {
				return this.removeProperty(n);
			}
			if (m && b[n]) {
				o = !!o;
			}
			(m) ? this[m] = o : this.setAttribute(n, "" + o);
			return this;
		},
		setProperties : function(m) {
			for (var n in m) {
				this.setProperty(n, m[n]);
			}
			return this;
		},
		getProperty : function(n) {
			var m = f[n];
			var o = (m) ? this[m] : this.getAttribute(n, 2);
			return (b[n]) ? !!o : (m) ? o : o || null;
		},
		getProperties : function() {
			var m = $A(arguments);
			return m.map(this.getProperty, this).associate(m);
		},
		removeProperty : function(n) {
			var m = f[n];
			(m) ? this[m] = (m && b[n]) ? false : "" : this.removeAttribute(n);
			return this;
		},
		removeProperties : function() {
			Array.each(arguments, this.removeProperty, this);
			return this;
		},
		hasClass : function(m) {
			return this.className.contains(m, " ");
		},
		addClass : function(m) {
			if (!this.hasClass(m)) {
				this.className = (this.className + " " + m).clean();
			}
			return this;
		},
		removeClass : function(m) {
			this.className = this.className.replace(new RegExp("(^|\\s)" + m
							+ "(?:\\s|$)"), "$1");
			return this;
		},
		toggleClass : function(m) {
			return this.hasClass(m) ? this.removeClass(m) : this.addClass(m);
		},
		adopt : function() {
			Array.flatten(arguments).each(function(m) {
						m = document.id(m, true);
						if (m) {
							this.appendChild(m);
						}
					}, this);
			return this;
		},
		appendText : function(n, m) {
			return this.grab(this.getDocument().newTextNode(n), m);
		},
		grab : function(n, m) {
			a[m || "bottom"](document.id(n, true), this);
			return this;
		},
		inject : function(n, m) {
			a[m || "bottom"](this, document.id(n, true));
			return this;
		},
		replaces : function(m) {
			m = document.id(m, true);
			m.parentNode.replaceChild(this, m);
			return this;
		},
		wraps : function(n, m) {
			n = document.id(n, true);
			return this.replaces(n).grab(n, m);
		},
		getPrevious : function(m, n) {
			return k(this, "previousSibling", null, m, false, n);
		},
		getAllPrevious : function(m, n) {
			return k(this, "previousSibling", null, m, true, n);
		},
		getNext : function(m, n) {
			return k(this, "nextSibling", null, m, false, n);
		},
		getAllNext : function(m, n) {
			return k(this, "nextSibling", null, m, true, n);
		},
		getFirst : function(m, n) {
			return k(this, "nextSibling", "firstChild", m, false, n);
		},
		getLast : function(m, n) {
			return k(this, "previousSibling", "lastChild", m, false, n);
		},
		getParent : function(m, n) {
			return k(this, "parentNode", null, m, false, n);
		},
		getParents : function(m, n) {
			return k(this, "parentNode", null, m, true, n);
		},
		getSiblings : function(m, n) {
			return this.getParent().getChildren(m, n).erase(this);
		},
		getChildren : function(m, n) {
			return k(this, "nextSibling", "firstChild", m, true, n);
		},
		getWindow : function() {
			return this.ownerDocument.window;
		},
		getDocument : function() {
			return this.ownerDocument;
		},
		getElementById : function(p, o) {
			var n = this.ownerDocument.getElementById(p);
			if (!n) {
				return null;
			}
			for (var m = n.parentNode; m != this; m = m.parentNode) {
				if (!m) {
					return null;
				}
			}
			return document.id(n, o);
		},
		getSelected : function() {
			return new Elements($A(this.options).filter(function(m) {
						return m.selected;
					}));
		},
		getComputedStyle : function(n) {
			if (this.currentStyle) {
				return this.currentStyle[n.camelCase()];
			}
			var m = this.getDocument().defaultView.getComputedStyle(this, null);
			return (m) ? m.getPropertyValue([n.hyphenate()]) : null;
		},
		toQueryString : function() {
			var m = [];
			this.getElements("input, select, textarea", true).each(function(n) {
				if (!n.name || n.disabled || n.type == "submit"
						|| n.type == "reset" || n.type == "file") {
					return;
				}
				var o = (n.tagName.toLowerCase() == "select")
						? Element.getSelected(n).map(function(p) {
									return p.value;
								})
						: ((n.type == "radio" || n.type == "checkbox") && !n.checked)
								? null
								: n.value;
				$splat(o).each(function(p) {
							if (typeof p != "undefined") {
								m.push(n.name + "=" + encodeURIComponent(p));
							}
						});
			});
			return m.join("&");
		},
		clone : function(p, m) {
			p = p !== false;
			var s = this.cloneNode(p);
			var o = function(w, v) {
				if (!m) {
					w.removeAttribute("id");
				}
				if (Browser.Engine.trident) {
					w.clearAttributes();
					w.mergeAttributes(v);
					w.removeAttribute("uid");
					if (w.options) {
						var x = w.options, t = v.options;
						for (var u = x.length; u--;) {
							x[u].selected = t[u].selected;
						}
					}
				}
				var y = j[v.tagName.toLowerCase()];
				if (y && v[y]) {
					w[y] = v[y];
				}
			};
			if (p) {
				var q = s.getElementsByTagName("*"), r = this
						.getElementsByTagName("*");
				for (var n = q.length; n--;) {
					o(q[n], r[n]);
				}
			}
			o(s, this);
			return document.id(s);
		},
		destroy : function() {
			Element.empty(this);
			Element.dispose(this);
			h(this, true);
			return null;
		},
		empty : function() {
			$A(this.childNodes).each(function(m) {
						Element.destroy(m);
					});
			return this;
		},
		dispose : function() {
			return (this.parentNode) ? this.parentNode.removeChild(this) : this;
		},
		hasChild : function(m) {
			m = document.id(m, true);
			if (!m) {
				return false;
			}
			if (Browser.Engine.webkit && Browser.Engine.version < 420) {
				return $A(this.getElementsByTagName(m.tagName)).contains(m);
			}
			return (this.contains) ? (this != m && this.contains(m)) : !!(this
					.compareDocumentPosition(m) & 16);
		},
		match : function(m) {
			return (!m || (m == this) || (Element.get(this, "tag") == m));
		}
	});
	Native.implement([Element, Window, Document], {
				addListener : function(p, o) {
					if (p == "unload") {
						var m = o, n = this;
						o = function() {
							n.removeListener("unload", o);
							m();
						};
					} else {
						i[this.uid] = this;
					}
					if (this.addEventListener) {
						this.addEventListener(p, o, false);
					} else {
						this.attachEvent("on" + p, o);
					}
					return this;
				},
				removeListener : function(n, m) {
					if (this.removeEventListener) {
						this.removeEventListener(n, m, false);
					} else {
						this.detachEvent("on" + n, m);
					}
					return this;
				},
				retrieve : function(n, m) {
					var p = c(this.uid), o = p[n];
					if (m != undefined && o == undefined) {
						o = p[n] = m;
					}
					return $pick(o);
				},
				store : function(n, m) {
					var o = c(this.uid);
					o[n] = m;
					return this;
				},
				eliminate : function(m) {
					var n = c(this.uid);
					delete n[m];
					return this;
				}
			});
	window.addListener("unload", d);
})();
Element.Properties = new Hash;
Element.Properties.style = {
	set : function(a) {
		this.style.cssText = a;
	},
	get : function() {
		return this.style.cssText;
	},
	erase : function() {
		this.style.cssText = "";
	}
};
Element.Properties.tag = {
	get : function() {
		return this.tagName.toLowerCase();
	}
};
Element.Properties.html = (function() {
	var c = document.createElement("div");
	var a = {
		table : [1, "<table>", "</table>"],
		select : [1, "<select>", "</select>"],
		tbody : [2, "<table><tbody>", "</tbody></table>"],
		tr : [3, "<table><tbody><tr>", "</tr></tbody></table>"]
	};
	a.thead = a.tfoot = a.tbody;
	var b = {
		set : function() {
			var f = Array.flatten(arguments).join("");
			var g = Browser.Engine.trident && a[this.get("tag")];
			if (g) {
				var h = c;
				h.innerHTML = g[1] + f + g[2];
				for (var d = g[0]; d--;) {
					h = h.firstChild;
				}
				this.empty().adopt(h.childNodes);
			} else {
				this.innerHTML = f;
			}
		}
	};
	b.erase = b.set;
	return b;
})();
if (Browser.Engine.webkit && Browser.Engine.version < 420) {
	Element.Properties.text = {
		get : function() {
			if (this.innerText) {
				return this.innerText;
			}
			var a = this.ownerDocument.newElement("div", {
						html : this.innerHTML
					}).inject(this.ownerDocument.body);
			var b = a.innerText;
			a.destroy();
			return b;
		}
	};
}
(function() {
	Element.implement({
		scrollTo : function(i, j) {
			if (b(this)) {
				this.getWindow().scrollTo(i, j);
			} else {
				this.scrollLeft = i;
				this.scrollTop = j;
			}
			return this;
		},
		getSize : function() {
			if (b(this)) {
				return this.getWindow().getSize();
			}
			return {
				x : this.offsetWidth,
				y : this.offsetHeight
			};
		},
		getScrollSize : function() {
			if (b(this)) {
				return this.getWindow().getScrollSize();
			}
			return {
				x : this.scrollWidth,
				y : this.scrollHeight
			};
		},
		getScroll : function() {
			if (b(this)) {
				return this.getWindow().getScroll();
			}
			return {
				x : this.scrollLeft,
				y : this.scrollTop
			};
		},
		getScrolls : function() {
			var j = this, i = {
				x : 0,
				y : 0
			};
			while (j && !b(j)) {
				i.x += j.scrollLeft;
				i.y += j.scrollTop;
				j = j.parentNode;
			}
			return i;
		},
		getOffsetParent : function() {
			var i = this;
			if (b(i)) {
				return null;
			}
			if (!Browser.Engine.trident) {
				return i.offsetParent;
			}
			while ((i = i.parentNode) && !b(i)) {
				if (d(i, "position") != "static") {
					return i;
				}
			}
			return null;
		},
		getOffsets : function() {
			if (this.getBoundingClientRect) {
				var k = this.getBoundingClientRect(), n = document.id(this
						.getDocument().documentElement), q = n.getScroll(), l = this
						.getScrolls(), j = this.getScroll(), i = (d(this,
						"position") == "fixed");
				return {
					x : k.left.toInt() + l.x - j.x + ((i) ? 0 : q.x)
							- n.clientLeft,
					y : k.top.toInt() + l.y - j.y + ((i) ? 0 : q.y)
							- n.clientTop
				};
			}
			var m = this, o = {
				x : 0,
				y : 0
			};
			if (b(this)) {
				return o;
			}
			while (m && !b(m)) {
				o.x += m.offsetLeft;
				o.y += m.offsetTop;
				if (Browser.Engine.gecko) {
					if (!g(m)) {
						o.x += c(m);
						o.y += h(m);
					}
					var p = m.parentNode;
					if (p && d(p, "overflow") != "visible") {
						o.x += c(p);
						o.y += h(p);
					}
				} else {
					if (m != this && Browser.Engine.webkit) {
						o.x += c(m);
						o.y += h(m);
					}
				}
				m = m.offsetParent;
			}
			if (Browser.Engine.gecko && !g(this)) {
				o.x -= c(this);
				o.y -= h(this);
			}
			return o;
		},
		getPosition : function(l) {
			if (b(this)) {
				return {
					x : 0,
					y : 0
				};
			}
			var m = this.getOffsets(), j = this.getScrolls();
			var i = {
				x : m.x - j.x,
				y : m.y - j.y
			};
			var k = (l && (l = document.id(l))) ? l.getPosition() : {
				x : 0,
				y : 0
			};
			return {
				x : i.x - k.x,
				y : i.y - k.y
			};
		},
		getCoordinates : function(k) {
			if (b(this)) {
				return this.getWindow().getCoordinates();
			}
			var i = this.getPosition(k), j = this.getSize();
			var l = {
				left : i.x,
				top : i.y,
				width : j.x,
				height : j.y
			};
			l.right = l.left + l.width;
			l.bottom = l.top + l.height;
			return l;
		},
		computePosition : function(i) {
			return {
				left : i.x - f(this, "margin-left"),
				top : i.y - f(this, "margin-top")
			};
		},
		setPosition : function(i) {
			return this.setStyles(this.computePosition(i));
		}
	});
	Native.implement([Document, Window], {
				getSize : function() {
					if (Browser.Engine.presto || Browser.Engine.webkit) {
						var j = this.getWindow();
						return {
							x : j.innerWidth,
							y : j.innerHeight
						};
					}
					var i = a(this);
					return {
						x : i.clientWidth,
						y : i.clientHeight
					};
				},
				getScroll : function() {
					var j = this.getWindow(), i = a(this);
					return {
						x : j.pageXOffset || i.scrollLeft,
						y : j.pageYOffset || i.scrollTop
					};
				},
				getScrollSize : function() {
					var j = a(this), i = this.getSize();
					return {
						x : Math.max(j.scrollWidth, i.x),
						y : Math.max(j.scrollHeight, i.y)
					};
				},
				getPosition : function() {
					return {
						x : 0,
						y : 0
					};
				},
				getCoordinates : function() {
					var i = this.getSize();
					return {
						top : 0,
						left : 0,
						bottom : i.y,
						right : i.x,
						height : i.y,
						width : i.x
					};
				}
			});
	var d = Element.getComputedStyle;
	function f(i, j) {
		return d(i, j).toInt() || 0;
	}
	function g(i) {
		return d(i, "-moz-box-sizing") == "border-box";
	}
	function h(i) {
		return f(i, "border-top-width");
	}
	function c(i) {
		return f(i, "border-left-width");
	}
	function b(i) {
		return (/^(?:body|html)$/i).test(i.tagName);
	}
	function a(i) {
		var j = i.getDocument();
		return (!j.compatMode || j.compatMode == "CSS1Compat")
				? j.html
				: j.body;
	}
})();
Element.alias("setPosition", "position");
Native.implement([Window, Document, Element], {
			getHeight : function() {
				return this.getSize().y;
			},
			getWidth : function() {
				return this.getSize().x;
			},
			getScrollTop : function() {
				return this.getScroll().y;
			},
			getScrollLeft : function() {
				return this.getScroll().x;
			},
			getScrollHeight : function() {
				return this.getScrollSize().y;
			},
			getScrollWidth : function() {
				return this.getScrollSize().x;
			},
			getTop : function() {
				return this.getPosition().y;
			},
			getLeft : function() {
				return this.getPosition().x;
			}
		});
var Event = new Native({
			name : "Event",
			initialize : function(a, g) {
				g = g || window;
				var l = g.document;
				a = a || g.event;
				if (a.$extended) {
					return a;
				}
				this.$extended = true;
				var k = a.type;
				var h = a.target || a.srcElement;
				while (h && h.nodeType == 3) {
					h = h.parentNode;
				}
				if (k.test(/key/)) {
					var b = a.which || a.keyCode;
					var n = Event.Keys.keyOf(b);
					if (k == "keydown") {
						var d = b - 111;
						if (d > 0 && d < 13) {
							n = "f" + d;
						}
					}
					n = n || String.fromCharCode(b).toLowerCase();
				} else {
					if (k.match(/(click|mouse|menu)/i)) {
						l = (!l.compatMode || l.compatMode == "CSS1Compat")
								? l.html
								: l.body;
						var j = {
							x : a.pageX || a.clientX + l.scrollLeft,
							y : a.pageY || a.clientY + l.scrollTop
						};
						var c = {
							x : (a.pageX) ? a.pageX - g.pageXOffset : a.clientX,
							y : (a.pageY) ? a.pageY - g.pageYOffset : a.clientY
						};
						if (k.match(/DOMMouseScroll|mousewheel/)) {
							var i = (a.wheelDelta)
									? a.wheelDelta / 120
									: -(a.detail || 0) / 3;
						}
						var f = (a.which == 3) || (a.button == 2);
						var m = null;
						if (k.match(/over|out/)) {
							switch (k) {
								case "mouseover" :
									m = a.relatedTarget || a.fromElement;
									break;
								case "mouseout" :
									m = a.relatedTarget || a.toElement;
							}
							if (!(function() {
								while (m && m.nodeType == 3) {
									m = m.parentNode;
								}
								return true;
							}).create({
										attempt : Browser.Engine.gecko
									})()) {
								m = false;
							}
						}
					}
				}
				return $extend(this, {
							event : a,
							type : k,
							page : j,
							client : c,
							rightClick : f,
							wheel : i,
							relatedTarget : m,
							target : h,
							code : b,
							key : n,
							shift : a.shiftKey,
							control : a.ctrlKey,
							alt : a.altKey,
							meta : a.metaKey
						});
			}
		});
Event.Keys = new Hash({
			enter : 13,
			up : 38,
			down : 40,
			left : 37,
			right : 39,
			esc : 27,
			space : 32,
			backspace : 8,
			tab : 9,
			"delete" : 46
		});
Event.implement({
			stop : function() {
				return this.stopPropagation().preventDefault();
			},
			stopPropagation : function() {
				if (this.event.stopPropagation) {
					this.event.stopPropagation();
				} else {
					this.event.cancelBubble = true;
				}
				return this;
			},
			preventDefault : function() {
				if (this.event.preventDefault) {
					this.event.preventDefault();
				} else {
					this.event.returnValue = false;
				}
				return this;
			}
		});
Element.Properties.events = {
	set : function(a) {
		this.addEvents(a);
	}
};
Native.implement([Element, Window, Document], {
			addEvent : function(f, h) {
				var i = this.retrieve("events", {});
				i[f] = i[f] || {
					keys : [],
					values : []
				};
				if (i[f].keys.contains(h)) {
					return this;
				}
				i[f].keys.push(h);
				var g = f, a = Element.Events.get(f), c = h, j = this;
				if (a) {
					if (a.onAdd) {
						a.onAdd.call(this, h);
					}
					if (a.condition) {
						c = function(k) {
							if (a.condition.call(this, k)) {
								return h.call(this, k);
							}
							return true;
						};
					}
					g = a.base || g;
				}
				var d = function() {
					return h.call(j);
				};
				var b = Element.NativeEvents[g];
				if (b) {
					if (b == 2) {
						d = function(k) {
							k = new Event(k, j.getWindow());
							if (c.call(j, k) === false) {
								k.stop();
							}
						};
					}
					this.addListener(g, d);
				}
				i[f].values.push(d);
				return this;
			},
			removeEvent : function(c, b) {
				var a = this.retrieve("events");
				if (!a || !a[c]) {
					return this;
				}
				var g = a[c].keys.indexOf(b);
				if (g == -1) {
					return this;
				}
				a[c].keys.splice(g, 1);
				var f = a[c].values.splice(g, 1)[0];
				var d = Element.Events.get(c);
				if (d) {
					if (d.onRemove) {
						d.onRemove.call(this, b);
					}
					c = d.base || c;
				}
				return (Element.NativeEvents[c])
						? this.removeListener(c, f)
						: this;
			},
			addEvents : function(a) {
				for (var b in a) {
					this.addEvent(b, a[b]);
				}
				return this;
			},
			removeEvents : function(a) {
				var c;
				if ($type(a) == "object") {
					for (c in a) {
						this.removeEvent(c, a[c]);
					}
					return this;
				}
				var b = this.retrieve("events");
				if (!b) {
					return this;
				}
				if (!a) {
					for (c in b) {
						this.removeEvents(c);
					}
					this.eliminate("events");
				} else {
					if (b[a]) {
						while (b[a].keys[0]) {
							this.removeEvent(a, b[a].keys[0]);
						}
						b[a] = null;
					}
				}
				return this;
			},
			fireEvent : function(d, b, a) {
				var c = this.retrieve("events");
				if (!c || !c[d]) {
					return this;
				}
				c[d].keys.each(function(f) {
							f.create({
										bind : this,
										delay : a,
										"arguments" : b
									})();
						}, this);
				return this;
			},
			cloneEvents : function(d, a) {
				d = document.id(d);
				var c = d.retrieve("events");
				if (!c) {
					return this;
				}
				if (!a) {
					for (var b in c) {
						this.cloneEvents(d, b);
					}
				} else {
					if (c[a]) {
						c[a].keys.each(function(f) {
									this.addEvent(a, f);
								}, this);
					}
				}
				return this;
			}
		});
try {
	if (typeof HTMLElement != "undefined") {
		HTMLElement.prototype.fireEvent = Element.prototype.fireEvent;
	}
} catch (e) {
}
Element.NativeEvents = {
	click : 2,
	dblclick : 2,
	mouseup : 2,
	mousedown : 2,
	contextmenu : 2,
	mousewheel : 2,
	DOMMouseScroll : 2,
	mouseover : 2,
	mouseout : 2,
	mousemove : 2,
	selectstart : 2,
	selectend : 2,
	keydown : 2,
	keypress : 2,
	keyup : 2,
	focus : 2,
	blur : 2,
	change : 2,
	reset : 2,
	select : 2,
	submit : 2,
	load : 1,
	unload : 1,
	beforeunload : 2,
	resize : 1,
	move : 1,
	DOMContentLoaded : 1,
	readystatechange : 1,
	error : 1,
	abort : 1,
	scroll : 1
};
(function() {
	var a = function(b) {
		var c = b.relatedTarget;
		if (c == undefined) {
			return true;
		}
		if (c === false) {
			return false;
		}
		return ($type(this) != "document" && c != this && c.prefix != "xul" && !this
				.hasChild(c));
	};
	Element.Events = new Hash({
				mouseenter : {
					base : "mouseover",
					condition : a
				},
				mouseleave : {
					base : "mouseout",
					condition : a
				},
				mousewheel : {
					base : (Browser.Engine.gecko)
							? "DOMMouseScroll"
							: "mousewheel"
				}
			});
})();
Element.Properties.styles = {
	set : function(a) {
		this.setStyles(a);
	}
};
Element.Properties.opacity = {
	set : function(a, b) {
		if (!b) {
			if (a == 0) {
				if (this.style.visibility != "hidden") {
					this.style.visibility = "hidden";
				}
			} else {
				if (this.style.visibility != "visible") {
					this.style.visibility = "visible";
				}
			}
		}
		if (!this.currentStyle || !this.currentStyle.hasLayout) {
			this.style.zoom = 1;
		}
		if (Browser.Engine.trident) {
			this.style.filter = (a == 1) ? "" : "alpha(opacity=" + a * 100
					+ ")";
		}
		this.style.opacity = a;
		this.store("opacity", a);
	},
	get : function() {
		return this.retrieve("opacity", 1);
	}
};
Element.implement({
	setOpacity : function(a) {
		return this.set("opacity", a, true);
	},
	getOpacity : function() {
		return this.get("opacity");
	},
	setStyle : function(b, a) {
		switch (b) {
			case "opacity" :
				return this.set("opacity", parseFloat(a));
			case "float" :
				b = (Browser.Engine.trident) ? "styleFloat" : "cssFloat";
		}
		b = b.camelCase();
		if ($type(a) != "string") {
			var c = (Element.Styles.get(b) || "@").split(" ");
			a = $splat(a).map(function(f, d) {
				if (!c[d]) {
					return "";
				}
				return ($type(f) == "number") ? c[d]
						.replace("@", Math.round(f)) : f;
			}).join(" ");
		} else {
			if (a == String(Number(a))) {
				a = Math.round(a);
			}
		}
		this.style[b] = a;
		return this;
	},
	getStyle : function(h) {
		switch (h) {
			case "opacity" :
				return this.get("opacity");
			case "float" :
				h = (Browser.Engine.trident) ? "styleFloat" : "cssFloat";
		}
		h = h.camelCase();
		var a = this.style[h];
		if (!$chk(a)) {
			a = [];
			for (var g in Element.ShortStyles) {
				if (h != g) {
					continue;
				}
				for (var f in Element.ShortStyles[g]) {
					a.push(this.getStyle(f));
				}
				return a.join(" ");
			}
			a = this.getComputedStyle(h);
		}
		if (a) {
			a = String(a);
			var c = a.match(/rgba?\([\d\s,]+\)/);
			if (c) {
				a = a.replace(c[0], c[0].rgbToHex());
			}
		}
		if (Browser.Engine.presto
				|| (Browser.Engine.trident && !$chk(parseInt(a, 10)))) {
			if (h.test(/^(height|width)$/)) {
				var b = (h == "width") ? ["left", "right"] : ["top", "bottom"], d = 0;
				b.each(function(i) {
							d += this.getStyle("border-" + i + "-width")
									.toInt()
									+ this.getStyle("padding-" + i).toInt();
						}, this);
				return this["offset" + h.capitalize()] - d + "px";
			}
			if ((Browser.Engine.presto) && String(a).test("px")) {
				return a;
			}
			if (h.test(/(border(.+)Width|margin|padding)/)) {
				return "0px";
			}
		}
		return a;
	},
	setStyles : function(b) {
		for (var a in b) {
			this.setStyle(a, b[a]);
		}
		return this;
	},
	getStyles : function() {
		var a = {};
		Array.flatten(arguments).each(function(b) {
					a[b] = this.getStyle(b);
				}, this);
		return a;
	}
});
Element.Styles = new Hash({
			left : "@px",
			top : "@px",
			bottom : "@px",
			right : "@px",
			width : "@px",
			height : "@px",
			maxWidth : "@px",
			maxHeight : "@px",
			minWidth : "@px",
			minHeight : "@px",
			backgroundColor : "rgb(@, @, @)",
			backgroundPosition : "@px @px",
			color : "rgb(@, @, @)",
			fontSize : "@px",
			letterSpacing : "@px",
			lineHeight : "@px",
			clip : "rect(@px @px @px @px)",
			margin : "@px @px @px @px",
			padding : "@px @px @px @px",
			border : "@px @ rgb(@, @, @) @px @ rgb(@, @, @) @px @ rgb(@, @, @)",
			borderWidth : "@px @px @px @px",
			borderStyle : "@ @ @ @",
			borderColor : "rgb(@, @, @) rgb(@, @, @) rgb(@, @, @) rgb(@, @, @)",
			zIndex : "@",
			zoom : "@",
			fontWeight : "@",
			textIndent : "@px",
			opacity : "@"
		});
Element.ShortStyles = {
	margin : {},
	padding : {},
	border : {},
	borderWidth : {},
	borderStyle : {},
	borderColor : {}
};
["Top", "Right", "Bottom", "Left"].each(function(h) {
			var g = Element.ShortStyles;
			var b = Element.Styles;
			["margin", "padding"].each(function(i) {
						var j = i + h;
						g[i][j] = b[j] = "@px";
					});
			var f = "border" + h;
			g.border[f] = b[f] = "@px @ rgb(@, @, @)";
			var d = f + "Width", a = f + "Style", c = f + "Color";
			g[f] = {};
			g.borderWidth[d] = g[f][d] = b[d] = "@px";
			g.borderStyle[a] = g[f][a] = b[a] = "@";
			g.borderColor[c] = g[f][c] = b[c] = "rgb(@, @, @)";
		});
var Fx = new Class({
			Implements : [Chain, Events, Options],
			options : {
				fps : 50,
				unit : false,
				duration : 500,
				link : "ignore"
			},
			initialize : function(a) {
				this.subject = this.subject || this;
				this.setOptions(a);
				this.options.duration = Fx.Durations[this.options.duration]
						|| this.options.duration.toInt();
				var b = this.options.wait;
				if (b === false) {
					this.options.link = "cancel";
				}
			},
			getTransition : function() {
				return function(a) {
					return -(Math.cos(Math.PI * a) - 1) / 2;
				};
			},
			step : function() {
				var a = $time();
				if (a < this.time + this.options.duration) {
					var b = this.transition((a - this.time)
							/ this.options.duration);
					this.set(this.compute(this.from, this.to, b));
				} else {
					this.set(this.compute(this.from, this.to, 1));
					this.complete();
				}
			},
			set : function(a) {
				return a;
			},
			compute : function(c, b, a) {
				return Fx.compute(c, b, a);
			},
			check : function() {
				if (!this.timer) {
					return true;
				}
				switch (this.options.link) {
					case "cancel" :
						this.cancel();
						return true;
					case "chain" :
						this.chain(this.caller.bind(this, arguments));
						return false;
				}
				return false;
			},
			start : function(b, a) {
				if (!this.check(b, a)) {
					return this;
				}
				this.from = b;
				this.to = a;
				this.time = 0;
				this.transition = this.getTransition();
				this.startTimer();
				this.onStart();
				return this;
			},
			complete : function() {
				if (this.stopTimer()) {
					this.onComplete();
				}
				return this;
			},
			cancel : function() {
				if (this.stopTimer()) {
					this.onCancel();
				}
				return this;
			},
			onStart : function() {
				this.fireEvent("start", this.subject);
			},
			onComplete : function() {
				this.fireEvent("complete", this.subject);
				if (!this.callChain()) {
					this.fireEvent("chainComplete", this.subject);
				}
			},
			onCancel : function() {
				this.fireEvent("cancel", this.subject).clearChain();
			},
			pause : function() {
				this.stopTimer();
				return this;
			},
			resume : function() {
				this.startTimer();
				return this;
			},
			stopTimer : function() {
				if (!this.timer) {
					return false;
				}
				this.time = $time() - this.time;
				this.timer = $clear(this.timer);
				return true;
			},
			startTimer : function() {
				if (this.timer) {
					return false;
				}
				this.time = $time() - this.time;
				this.timer = this.step.periodical(Math.round(1000
								/ this.options.fps), this);
				return true;
			}
		});
Fx.compute = function(c, b, a) {
	return (b - c) * a + c;
};
Fx.Durations = {
	"short" : 250,
	normal : 500,
	"long" : 1000
};
Fx.CSS = new Class({
	Extends : Fx,
	prepare : function(d, f, b) {
		b = $splat(b);
		var c = b[1];
		if (!$chk(c)) {
			b[1] = b[0];
			b[0] = d.getStyle(f);
		}
		var a = b.map(this.parse);
		return {
			from : a[0],
			to : a[1]
		};
	},
	parse : function(a) {
		a = $lambda(a)();
		a = (typeof a == "string") ? a.split(" ") : $splat(a);
		return a.map(function(c) {
					c = String(c);
					var b = false;
					Fx.CSS.Parsers.each(function(g, f) {
								if (b) {
									return;
								}
								var d = g.parse(c);
								if ($chk(d)) {
									b = {
										value : d,
										parser : g
									};
								}
							});
					b = b || {
						value : c,
						parser : Fx.CSS.Parsers.String
					};
					return b;
				});
	},
	compute : function(d, c, b) {
		var a = [];
		(Math.min(d.length, c.length)).times(function(f) {
					a.push({
								value : d[f].parser.compute(d[f].value,
										c[f].value, b),
								parser : d[f].parser
							});
				});
		a.$family = {
			name : "fx:css:value"
		};
		return a;
	},
	serve : function(c, b) {
		if ($type(c) != "fx:css:value") {
			c = this.parse(c);
		}
		var a = [];
		c.each(function(d) {
					a = a.concat(d.parser.serve(d.value, b));
				});
		return a;
	},
	render : function(a, d, c, b) {
		a.setStyle(d, this.serve(c, b));
	},
	search : function(a) {
		if (Fx.CSS.Cache[a]) {
			return Fx.CSS.Cache[a];
		}
		var b = {};
		Array.each(document.styleSheets, function(f, d) {
					var c = f.href;
					if (c && c.contains("://") && !c.contains(document.domain)) {
						return;
					}
					var g = f.rules || f.cssRules;
					Array.each(g, function(k, h) {
								if (!k.style) {
									return;
								}
								var j = (k.selectorText) ? k.selectorText
										.replace(/^\w+/, function(i) {
													return i.toLowerCase();
												}) : null;
								if (!j || !j.test("^" + a + "$")) {
									return;
								}
								Element.Styles.each(function(l, i) {
											if (!k.style[i]
													|| Element.ShortStyles[i]) {
												return;
											}
											l = String(k.style[i]);
											b[i] = (l.test(/^rgb/)) ? l
													.rgbToHex() : l;
										});
							});
				});
		return Fx.CSS.Cache[a] = b;
	}
});
Fx.CSS.Cache = {};
Fx.CSS.Parsers = new Hash({
			Color : {
				parse : function(a) {
					if (a.match(/^#[0-9a-f]{3,6}$/i)) {
						return a.hexToRgb(true);
					}
					return ((a = a.match(/(\d+),\s*(\d+),\s*(\d+)/))) ? [a[1],
							a[2], a[3]] : false;
				},
				compute : function(c, b, a) {
					return c.map(function(f, d) {
								return Math.round(Fx.compute(c[d], b[d], a));
							});
				},
				serve : function(a) {
					return a.map(Number);
				}
			},
			Number : {
				parse : parseFloat,
				compute : Fx.compute,
				serve : function(b, a) {
					return (a) ? b + a : b;
				}
			},
			String : {
				parse : $lambda(false),
				compute : $arguments(1),
				serve : $arguments(0)
			}
		});
Fx.Morph = new Class({
			Extends : Fx.CSS,
			initialize : function(b, a) {
				this.element = this.subject = document.id(b);
				this.parent(a);
			},
			set : function(a) {
				if (typeof a == "string") {
					a = this.search(a);
				}
				for (var b in a) {
					this.render(this.element, b, a[b], this.options.unit);
				}
				return this;
			},
			compute : function(f, d, c) {
				var a = {};
				for (var b in f) {
					a[b] = this.parent(f[b], d[b], c);
				}
				return a;
			},
			start : function(b) {
				if (!this.check(b)) {
					return this;
				}
				if (typeof b == "string") {
					b = this.search(b);
				}
				var f = {}, d = {};
				for (var c in b) {
					var a = this.prepare(this.element, c, b[c]);
					f[c] = a.from;
					d[c] = a.to;
				}
				return this.parent(f, d);
			}
		});
Element.Properties.morph = {
	set : function(a) {
		var b = this.retrieve("morph");
		if (b) {
			b.cancel();
		}
		return this.eliminate("morph").store("morph:options", $extend({
							link : "cancel"
						}, a));
	},
	get : function(a) {
		if (a || !this.retrieve("morph")) {
			if (a || !this.retrieve("morph:options")) {
				this.set("morph", a);
			}
			this.store("morph", new Fx.Morph(this, this
									.retrieve("morph:options")));
		}
		return this.retrieve("morph");
	}
};
Element.implement({
			morph : function(a) {
				this.get("morph").start(a);
				return this;
			}
		});
Fx.implement({
			getTransition : function() {
				var a = this.options.transition
						|| Fx.Transitions.Sine.easeInOut;
				if (typeof a == "string") {
					var b = a.split(":");
					a = Fx.Transitions;
					a = a[b[0]] || a[b[0].capitalize()];
					if (b[1]) {
						a = a["ease" + b[1].capitalize()
								+ (b[2] ? b[2].capitalize() : "")];
					}
				}
				return a;
			}
		});
Fx.Transition = function(b, a) {
	a = $splat(a);
	return $extend(b, {
				easeIn : function(c) {
					return b(c, a);
				},
				easeOut : function(c) {
					return 1 - b(1 - c, a);
				},
				easeInOut : function(c) {
					return (c <= 0.5)
							? b(2 * c, a) / 2
							: (2 - b(2 * (1 - c), a)) / 2;
				}
			});
};
Fx.Transitions = new Hash({
			linear : $arguments(0)
		});
Fx.Transitions.extend = function(a) {
	for (var b in a) {
		Fx.Transitions[b] = new Fx.Transition(a[b]);
	}
};
Fx.Transitions.extend({
			Pow : function(b, a) {
				return Math.pow(b, a[0] || 6);
			},
			Expo : function(a) {
				return Math.pow(2, 8 * (a - 1));
			},
			Circ : function(a) {
				return 1 - Math.sin(Math.acos(a));
			},
			Sine : function(a) {
				return 1 - Math.sin((1 - a) * Math.PI / 2);
			},
			Back : function(b, a) {
				a = a[0] || 1.618;
				return Math.pow(b, 2) * ((a + 1) * b - a);
			},
			Bounce : function(g) {
				var f;
				for (var d = 0, c = 1; 1; d += c, c /= 2) {
					if (g >= (7 - 4 * d) / 11) {
						f = c * c - Math.pow((11 - 6 * d - 11 * g) / 4, 2);
						break;
					}
				}
				return f;
			},
			Elastic : function(b, a) {
				return Math.pow(2, 10 * --b)
						* Math.cos(20 * b * Math.PI * (a[0] || 1) / 3);
			}
		});
["Quad", "Cubic", "Quart", "Quint"].each(function(b, a) {
			Fx.Transitions[b] = new Fx.Transition(function(c) {
						return Math.pow(c, [a + 2]);
					});
		});
Fx.Tween = new Class({
			Extends : Fx.CSS,
			initialize : function(b, a) {
				this.element = this.subject = document.id(b);
				this.parent(a);
			},
			set : function(b, a) {
				if (arguments.length == 1) {
					a = b;
					b = this.property || this.options.property;
				}
				this.render(this.element, b, a, this.options.unit);
				return this;
			},
			start : function(c, f, d) {
				if (!this.check(c, f, d)) {
					return this;
				}
				var b = Array.flatten(arguments);
				this.property = this.options.property || b.shift();
				var a = this.prepare(this.element, this.property, b);
				return this.parent(a.from, a.to);
			}
		});
Element.Properties.tween = {
	set : function(a) {
		var b = this.retrieve("tween");
		if (b) {
			b.cancel();
		}
		return this.eliminate("tween").store("tween:options", $extend({
							link : "cancel"
						}, a));
	},
	get : function(a) {
		if (a || !this.retrieve("tween")) {
			if (a || !this.retrieve("tween:options")) {
				this.set("tween", a);
			}
			this.store("tween", new Fx.Tween(this, this
									.retrieve("tween:options")));
		}
		return this.retrieve("tween");
	}
};
Element.implement({
			tween : function(a, c, b) {
				this.get("tween").start(arguments);
				return this;
			},
			fade : function(c) {
				var f = this.get("tween"), d = "opacity", a;
				c = $pick(c, "toggle");
				switch (c) {
					case "in" :
						f.start(d, 1);
						break;
					case "out" :
						f.start(d, 0);
						break;
					case "show" :
						f.set(d, 1);
						break;
					case "hide" :
						f.set(d, 0);
						break;
					case "toggle" :
						var b = this.retrieve("fade:flag",
								this.get("opacity") == 1);
						f.start(d, (b) ? 0 : 1);
						this.store("fade:flag", !b);
						a = true;
						break;
					default :
						f.start(d, arguments);
				}
				if (!a) {
					this.eliminate("fade:flag");
				}
				return this;
			},
			highlight : function(c, a) {
				if (!a) {
					a = this.retrieve("highlight:original", this
									.getStyle("background-color"));
					a = (a == "transparent") ? "#fff" : a;
				}
				var b = this.get("tween");
				b.start("background-color", c || "#ffff88", a).chain(
						function() {
							this.setStyle("background-color", this
											.retrieve("highlight:original"));
							b.callChain();
						}.bind(this));
				return this;
			}
		});
var Request = new Class({
	Implements : [Chain, Events, Options],
	options : {
		url : "",
		data : "",
		headers : {
			"X-Requested-With" : "XMLHttpRequest",
			Accept : "text/javascript, text/html, application/xml, text/xml, */*"
		},
		async : true,
		format : false,
		method : "post",
		link : "ignore",
		isSuccess : null,
		emulation : true,
		urlEncoded : true,
		encoding : "utf-8",
		evalScripts : false,
		evalResponse : false,
		noCache : false
	},
	initialize : function(a) {
		this.xhr = new Browser.Request();
		this.setOptions(a);
		this.options.isSuccess = this.options.isSuccess || this.isSuccess;
		this.headers = new Hash(this.options.headers);
	},
	onStateChange : function() {
		if (this.xhr.readyState != 4 || !this.running) {
			return;
		}
		this.running = false;
		this.status = 0;
		$try(function() {
					this.status = this.xhr.status;
				}.bind(this));
		this.xhr.onreadystatechange = $empty;
		if (this.options.isSuccess.call(this, this.status)) {
			this.response = {
				text : this.xhr.responseText,
				xml : this.xhr.responseXML
			};
			this.success(this.response.text, this.response.xml);
		} else {
			this.response = {
				text : null,
				xml : null
			};
			this.failure();
		}
	},
	isSuccess : function() {
		return ((this.status >= 200) && (this.status < 300));
	},
	processScripts : function(a) {
		if (this.options.evalResponse
				|| (/(ecma|java)script/).test(this.getHeader("Content-type"))) {
			return $exec(a);
		}
		return a.stripScripts(this.options.evalScripts);
	},
	success : function(b, a) {
		this.onSuccess(this.processScripts(b), a);
	},
	onSuccess : function() {
		this.fireEvent("complete", arguments).fireEvent("success", arguments)
				.callChain();
	},
	failure : function() {
		this.onFailure();
	},
	onFailure : function() {
		this.fireEvent("complete").fireEvent("failure", this.xhr);
	},
	setHeader : function(a, b) {
		this.headers.set(a, b);
		return this;
	},
	getHeader : function(a) {
		return $try(function() {
					return this.xhr.getResponseHeader(a);
				}.bind(this));
	},
	check : function() {
		if (!this.running) {
			return true;
		}
		switch (this.options.link) {
			case "cancel" :
				this.cancel();
				return true;
			case "chain" :
				this.chain(this.caller.bind(this, arguments));
				return false;
		}
		return false;
	},
	send : function(l) {
		if (!this.check(l)) {
			return this;
		}
		this.running = true;
		var j = $type(l);
		if (j == "string" || j == "element") {
			l = {
				data : l
			};
		}
		var d = this.options;
		l = $extend({
					data : d.data,
					url : d.url,
					method : d.method
				}, l);
		var h = l.data, b = String(l.url), a = l.method.toLowerCase();
		switch ($type(h)) {
			case "element" :
				h = document.id(h).toQueryString();
				break;
			case "object" :
			case "hash" :
				h = Hash.toQueryString(h);
		}
		if (this.options.format) {
			var k = "format=" + this.options.format;
			h = (h) ? k + "&" + h : k;
		}
		if (this.options.emulation && !["get", "post"].contains(a)) {
			var i = "_method=" + a;
			h = (h) ? i + "&" + h : i;
			a = "post";
		}
		if (this.options.urlEncoded && a == "post") {
			var c = (this.options.encoding) ? "; charset="
					+ this.options.encoding : "";
			this.headers.set("Content-type",
					"application/x-www-form-urlencoded" + c);
		}
		if (this.options.noCache) {
			var g = "noCache=" + new Date().getTime();
			h = (h) ? g + "&" + h : g;
		}
		var f = b.lastIndexOf("/");
		if (f > -1 && (f = b.indexOf("#")) > -1) {
			b = b.substr(0, f);
		}
		if (h && a == "get") {
			b = b + (b.contains("?") ? "&" : "?") + h;
			h = null;
		}
		this.xhr.open(a.toUpperCase(), b, this.options.async);
		this.xhr.onreadystatechange = this.onStateChange.bind(this);
		this.headers.each(function(n, m) {
					try {
						this.xhr.setRequestHeader(m, n);
					} catch (o) {
						this.fireEvent("exception", [m, n]);
					}
				}, this);
		this.fireEvent("request");
		this.xhr.send(h);
		if (!this.options.async) {
			this.onStateChange();
		}
		return this;
	},
	cancel : function() {
		if (!this.running) {
			return this;
		}
		this.running = false;
		this.xhr.abort();
		this.xhr.onreadystatechange = $empty;
		this.xhr = new Browser.Request();
		this.fireEvent("cancel");
		return this;
	}
});
(function() {
	var a = {};
	["get", "post", "put", "delete", "GET", "POST", "PUT", "DELETE"].each(
			function(b) {
				a[b] = function() {
					var c = Array.link(arguments, {
								url : String.type,
								data : $defined
							});
					return this.send($extend(c, {
								method : b
							}));
				};
			});
	Request.implement(a);
})();
Element.Properties.send = {
	set : function(a) {
		var b = this.retrieve("send");
		if (b) {
			b.cancel();
		}
		return this.eliminate("send").store("send:options", $extend({
							data : this,
							link : "cancel",
							method : this.get("method") || "post",
							url : this.get("action")
						}, a));
	},
	get : function(a) {
		if (a || !this.retrieve("send")) {
			if (a || !this.retrieve("send:options")) {
				this.set("send", a);
			}
			this.store("send", new Request(this.retrieve("send:options")));
		}
		return this.retrieve("send");
	}
};
Element.implement({
			send : function(a) {
				var b = this.get("send");
				b.send({
							data : this,
							url : a || b.options.url
						});
				return this;
			}
		});
Request.HTML = new Class({
			Extends : Request,
			options : {
				update : false,
				append : false,
				evalScripts : true,
				filter : false
			},
			processHTML : function(c) {
				var b = c.match(/<body[^>]*>([\s\S]*?)<\/body>/i);
				c = (b) ? b[1] : c;
				var a = new Element("div");
				return $try(function() {
							var d = "<root>" + c + "</root>", h;
							if (Browser.Engine.trident) {
								h = new ActiveXObject("Microsoft.XMLDOM");
								h.async = false;
								h.loadXML(d);
							} else {
								h = new DOMParser().parseFromString(d,
										"text/xml");
							}
							d = h.getElementsByTagName("root")[0];
							if (!d) {
								return null;
							}
							for (var g = 0, f = d.childNodes.length; g < f; g++) {
								var j = Element.clone(d.childNodes[g], true,
										true);
								if (j) {
									a.grab(j);
								}
							}
							return a;
						})
						|| a.set("html", c);
			},
			success : function(d) {
				var c = this.options, b = this.response;
				b.html = d.stripScripts(function(f) {
							b.javascript = f;
						});
				var a = this.processHTML(b.html);
				b.tree = a.childNodes;
				b.elements = a.getElements("*");
				if (c.filter) {
					b.tree = b.elements.filter(c.filter);
				}
				if (c.update) {
					document.id(c.update).empty().set("html", b.html);
				} else {
					if (c.append) {
						document.id(c.append).adopt(a.getChildren());
					}
				}
				if (c.evalScripts) {
					$exec(b.javascript);
				}
				this.onSuccess(b.tree, b.elements, b.html, b.javascript);
			}
		});
Element.Properties.load = {
	set : function(a) {
		var b = this.retrieve("load");
		if (b) {
			b.cancel();
		}
		return this.eliminate("load").store("load:options", $extend({
							data : this,
							link : "cancel",
							update : this,
							method : "get"
						}, a));
	},
	get : function(a) {
		if (a || !this.retrieve("load")) {
			if (a || !this.retrieve("load:options")) {
				this.set("load", a);
			}
			this.store("load", new Request.HTML(this.retrieve("load:options")));
		}
		return this.retrieve("load");
	}
};
Element.implement({
			load : function() {
				this.get("load").send(Array.link(arguments, {
							data : Object.type,
							url : String.type
						}));
				return this;
			}
		});
var JSON = new Hash(this.JSON && {
	stringify : JSON.stringify,
	parse : JSON.parse
}).extend({
			$specialChars : {
				"\b" : "\\b",
				"\t" : "\\t",
				"\n" : "\\n",
				"\f" : "\\f",
				"\r" : "\\r",
				'"' : '\\"',
				"\\" : "\\\\"
			},
			$replaceChars : function(a) {
				return JSON.$specialChars[a] || "\\u00"
						+ Math.floor(a.charCodeAt() / 16).toString(16)
						+ (a.charCodeAt() % 16).toString(16);
			},
			encode : function(b) {
				switch ($type(b)) {
					case "string" :
						return '"'
								+ b.replace(/[\x00-\x1f\\"]/g,
										JSON.$replaceChars) + '"';
					case "array" :
						return "[" + String(b.map(JSON.encode).clean()) + "]";
					case "object" :
					case "hash" :
						var a = [];
						Hash.each(b, function(f, d) {
									var c = JSON.encode(f);
									if (c) {
										a.push(JSON.encode(d) + ":" + c);
									}
								});
						return "{" + a + "}";
					case "number" :
					case "boolean" :
						return String(b);
					case false :
						return "null";
				}
				return null;
			},
			decode : function(string, secure) {
				if ($type(string) != "string" || !string.length) {
					return null;
				}
				if (secure
						&& !(/^[,:{}\[\]0-9.\-+Eaeflnr-u \n\r\t]*$/)
								.test(string.replace(/\\./g, "@").replace(
										/"[^"\\\n\r]*"/g, ""))) {
					return null;
				}
				return eval("(" + string + ")");
			}
		});
Request.JSON = new Class({
			Extends : Request,
			options : {
				secure : true
			},
			initialize : function(a) {
				this.parent(a);
				this.headers.extend({
							Accept : "application/json",
							"X-Request" : "JSON"
						});
			},
			success : function(a) {
				this.response.json = JSON.decode(a, this.options.secure);
				this.onSuccess(this.response.json, a);
			}
		});
var Cookie = new Class({
			Implements : Options,
			options : {
				path : false,
				domain : false,
				duration : false,
				secure : false,
				document : document
			},
			initialize : function(b, a) {
				this.key = b;
				this.setOptions(a);
			},
			write : function(b) {
				b = encodeURIComponent(b);
				if (this.options.domain) {
					b += "; domain=" + this.options.domain;
				}
				if (this.options.path) {
					b += "; path=" + this.options.path;
				}
				if (this.options.duration) {
					var a = new Date();
					a.setTime(a.getTime() + this.options.duration * 24 * 60
							* 60 * 1000);
					b += "; expires=" + a.toGMTString();
				}
				if (this.options.secure) {
					b += "; secure";
				}
				this.options.document.cookie = this.key + "=" + b;
				return this;
			},
			read : function() {
				var a = this.options.document.cookie.match("(?:^|;)\\s*"
						+ this.key.escapeRegExp() + "=([^;]*)");
				return (a) ? decodeURIComponent(a[1]) : null;
			},
			dispose : function() {
				new Cookie(this.key, $merge(this.options, {
									duration : -1
								})).write("");
				return this;
			}
		});
Cookie.write = function(b, c, a) {
	return new Cookie(b, a).write(c);
};
Cookie.read = function(a) {
	return new Cookie(a).read();
};
Cookie.dispose = function(b, a) {
	return new Cookie(b, a).dispose();
};
Element.Events.domready = {
	onAdd : function(a) {
		if (Browser.loaded) {
			a.call(this);
		}
	}
};
(function() {
	var b = function() {
		if (Browser.loaded) {
			return;
		}
		Browser.loaded = true;
		window.fireEvent("domready");
		document.fireEvent("domready");
	};
	window.addEvent("load", b);
	if (Browser.Engine.trident) {
		var a = document.createElement("div");
		(function() {
			($try(function() {
						a.doScroll();
						return document.id(a).inject(document.body).set("html",
								"temp").dispose();
					})) ? b() : arguments.callee.delay(50);
		})();
	} else {
		if (Browser.Engine.webkit && Browser.Engine.version < 525) {
			(function() {
				(["loaded", "complete"].contains(document.readyState))
						? b()
						: arguments.callee.delay(50);
			})();
		} else {
			document.addEvent("DOMContentLoaded", b);
		}
	}
})();
Native.implement([Document, Element], {
			getElements : function(j, h) {
				j = j.split(",");
				var c, f = {};
				for (var d = 0, b = j.length; d < b; d++) {
					var a = j[d], g = Selectors.Utils.search(this, a, f);
					if (d != 0 && g.item) {
						g = $A(g);
					}
					c = (d == 0) ? g : (c.item) ? $A(c).concat(g) : c.concat(g);
				}
				return new Elements(c, {
							ddup : (j.length > 1),
							cash : !h
						});
			}
		});
Element.implement({
			match : function(b) {
				if (!b || (b == this)) {
					return true;
				}
				var d = Selectors.Utils.parseTagAndID(b);
				var a = d[0], f = d[1];
				if (!Selectors.Filters.byID(this, f)
						|| !Selectors.Filters.byTag(this, a)) {
					return false;
				}
				var c = Selectors.Utils.parseSelector(b);
				return (c) ? Selectors.Utils.filter(this, c, {}) : true;
			}
		});
var Selectors = {
	Cache : {
		nth : {},
		parsed : {}
	}
};
Selectors.RegExps = {
	id : (/#([\w-]+)/),
	tag : (/^(\w+|\*)/),
	quick : (/^(\w+|\*)$/),
	splitter : (/\s*([+>~\s])\s*([a-zA-Z#.*:\[])/g),
	combined : (/\.([\w-]+)|\[(\w+)(?:([!*^$~|]?=)(["']?)([^\4]*?)\4)?\]|:([\w-]+)(?:\(["']?(.*?)?["']?\)|$)/g)
};
Selectors.Utils = {
	chk : function(b, c) {
		if (!c) {
			return true;
		}
		var a = $uid(b);
		if (!c[a]) {
			return c[a] = true;
		}
		return false;
	},
	parseNthArgument : function(i) {
		if (Selectors.Cache.nth[i]) {
			return Selectors.Cache.nth[i];
		}
		var f = i.match(/^([+-]?\d*)?([a-z]+)?([+-]?\d*)?$/);
		if (!f) {
			return false;
		}
		var h = parseInt(f[1], 10);
		var d = (h || h === 0) ? h : 1;
		var g = f[2] || false;
		var c = parseInt(f[3], 10) || 0;
		if (d != 0) {
			c--;
			while (c < 1) {
				c += d;
			}
			while (c >= d) {
				c -= d;
			}
		} else {
			d = c;
			g = "index";
		}
		switch (g) {
			case "n" :
				f = {
					a : d,
					b : c,
					special : "n"
				};
				break;
			case "odd" :
				f = {
					a : 2,
					b : 0,
					special : "n"
				};
				break;
			case "even" :
				f = {
					a : 2,
					b : 1,
					special : "n"
				};
				break;
			case "first" :
				f = {
					a : 0,
					special : "index"
				};
				break;
			case "last" :
				f = {
					special : "last-child"
				};
				break;
			case "only" :
				f = {
					special : "only-child"
				};
				break;
			default :
				f = {
					a : (d - 1),
					special : "index"
				};
		}
		return Selectors.Cache.nth[i] = f;
	},
	parseSelector : function(f) {
		if (Selectors.Cache.parsed[f]) {
			return Selectors.Cache.parsed[f];
		}
		var d, i = {
			classes : [],
			pseudos : [],
			attributes : []
		};
		while ((d = Selectors.RegExps.combined.exec(f))) {
			var j = d[1], h = d[2], g = d[3], b = d[5], c = d[6], k = d[7];
			if (j) {
				i.classes.push(j);
			} else {
				if (c) {
					var a = Selectors.Pseudo.get(c);
					if (a) {
						i.pseudos.push({
									parser : a,
									argument : k
								});
					} else {
						i.attributes.push({
									name : c,
									operator : "=",
									value : k
								});
					}
				} else {
					if (h) {
						i.attributes.push({
									name : h,
									operator : g,
									value : b
								});
					}
				}
			}
		}
		if (!i.classes.length) {
			delete i.classes;
		}
		if (!i.attributes.length) {
			delete i.attributes;
		}
		if (!i.pseudos.length) {
			delete i.pseudos;
		}
		if (!i.classes && !i.attributes && !i.pseudos) {
			i = null;
		}
		return Selectors.Cache.parsed[f] = i;
	},
	parseTagAndID : function(b) {
		var a = b.match(Selectors.RegExps.tag);
		var c = b.match(Selectors.RegExps.id);
		return [(a) ? a[1] : "*", (c) ? c[1] : false];
	},
	filter : function(g, c, f) {
		var d;
		if (c.classes) {
			for (d = c.classes.length; d--; d) {
				var h = c.classes[d];
				if (!Selectors.Filters.byClass(g, h)) {
					return false;
				}
			}
		}
		if (c.attributes) {
			for (d = c.attributes.length; d--; d) {
				var b = c.attributes[d];
				if (!Selectors.Filters.byAttribute(g, b.name, b.operator,
						b.value)) {
					return false;
				}
			}
		}
		if (c.pseudos) {
			for (d = c.pseudos.length; d--; d) {
				var a = c.pseudos[d];
				if (!Selectors.Filters.byPseudo(g, a.parser, a.argument, f)) {
					return false;
				}
			}
		}
		return true;
	},
	getByTagAndID : function(b, a, d) {
		if (d) {
			var c = (b.getElementById) ? b.getElementById(d, true) : Element
					.getElementById(b, d, true);
			return (c && Selectors.Filters.byTag(c, a)) ? [c] : [];
		} else {
			return b.getElementsByTagName(a);
		}
	},
	search : function(p, o, u) {
		var b = [];
		var c = o.trim().replace(Selectors.RegExps.splitter, function(k, j, i) {
					b.push(j);
					return ":)" + i;
				}).split(":)");
		var q, f, B;
		for (var A = 0, w = c.length; A < w; A++) {
			var z = c[A];
			if (A == 0 && Selectors.RegExps.quick.test(z)) {
				q = p.getElementsByTagName(z);
				continue;
			}
			var a = b[A - 1];
			var r = Selectors.Utils.parseTagAndID(z);
			var C = r[0], s = r[1];
			if (A == 0) {
				q = Selectors.Utils.getByTagAndID(p, C, s);
			} else {
				var d = {}, h = [];
				for (var y = 0, x = q.length; y < x; y++) {
					h = Selectors.Getters[a](h, q[y], C, s, d);
				}
				q = h;
			}
			var g = Selectors.Utils.parseSelector(z);
			if (g) {
				f = [];
				for (var v = 0, t = q.length; v < t; v++) {
					B = q[v];
					if (Selectors.Utils.filter(B, g, u)) {
						f.push(B);
					}
				}
				q = f;
			}
		}
		return q;
	}
};
Selectors.Getters = {
	" " : function(j, h, k, a, f) {
		var d = Selectors.Utils.getByTagAndID(h, k, a);
		for (var c = 0, b = d.length; c < b; c++) {
			var g = d[c];
			if (Selectors.Utils.chk(g, f)) {
				j.push(g);
			}
		}
		return j;
	},
	">" : function(j, h, k, a, g) {
		var c = Selectors.Utils.getByTagAndID(h, k, a);
		for (var f = 0, d = c.length; f < d; f++) {
			var b = c[f];
			if (b.parentNode == h && Selectors.Utils.chk(b, g)) {
				j.push(b);
			}
		}
		return j;
	},
	"+" : function(c, b, a, f, d) {
		while ((b = b.nextSibling)) {
			if (b.nodeType == 1) {
				if (Selectors.Utils.chk(b, d) && Selectors.Filters.byTag(b, a)
						&& Selectors.Filters.byID(b, f)) {
					c.push(b);
				}
				break;
			}
		}
		return c;
	},
	"~" : function(c, b, a, f, d) {
		while ((b = b.nextSibling)) {
			if (b.nodeType == 1) {
				if (!Selectors.Utils.chk(b, d)) {
					break;
				}
				if (Selectors.Filters.byTag(b, a)
						&& Selectors.Filters.byID(b, f)) {
					c.push(b);
				}
			}
		}
		return c;
	}
};
Selectors.Filters = {
	byTag : function(b, a) {
		return (a == "*" || (b.tagName && b.tagName.toLowerCase() == a));
	},
	byID : function(a, b) {
		return (!b || (a.id && a.id == b));
	},
	byClass : function(b, a) {
		return (b.className && b.className.contains && b.className.contains(a,
				" "));
	},
	byPseudo : function(a, d, c, b) {
		return d.call(a, c, b);
	},
	byAttribute : function(c, d, b, f) {
		var a = Element.prototype.getProperty.call(c, d);
		if (!a) {
			return (b == "!=");
		}
		if (!b || f == undefined) {
			return true;
		}
		switch (b) {
			case "=" :
				return (a == f);
			case "*=" :
				return (a.contains(f));
			case "^=" :
				return (a.substr(0, f.length) == f);
			case "$=" :
				return (a.substr(a.length - f.length) == f);
			case "!=" :
				return (a != f);
			case "~=" :
				return a.contains(f, " ");
			case "|=" :
				return a.contains(f, "-");
		}
		return false;
	}
};
Selectors.Pseudo = new Hash({
			checked : function() {
				return this.checked;
			},
			empty : function() {
				return !(this.innerText || this.textContent || "").length;
			},
			not : function(a) {
				return !Element.match(this, a);
			},
			contains : function(a) {
				return (this.innerText || this.textContent || "").contains(a);
			},
			"first-child" : function() {
				return Selectors.Pseudo.index.call(this, 0);
			},
			"last-child" : function() {
				var a = this;
				while ((a = a.nextSibling)) {
					if (a.nodeType == 1) {
						return false;
					}
				}
				return true;
			},
			"only-child" : function() {
				var b = this;
				while ((b = b.previousSibling)) {
					if (b.nodeType == 1) {
						return false;
					}
				}
				var a = this;
				while ((a = a.nextSibling)) {
					if (a.nodeType == 1) {
						return false;
					}
				}
				return true;
			},
			"nth-child" : function(h, f) {
				h = (h == undefined) ? "n" : h;
				var c = Selectors.Utils.parseNthArgument(h);
				if (c.special != "n") {
					return Selectors.Pseudo[c.special].call(this, c.a, f);
				}
				var g = 0;
				f.positions = f.positions || {};
				var d = $uid(this);
				if (!f.positions[d]) {
					var b = this;
					while ((b = b.previousSibling)) {
						if (b.nodeType != 1) {
							continue;
						}
						g++;
						var a = f.positions[$uid(b)];
						if (a != undefined) {
							g = a + g;
							break;
						}
					}
					f.positions[d] = g;
				}
				return (f.positions[d] % c.a == c.b);
			},
			index : function(a) {
				var b = this, c = 0;
				while ((b = b.previousSibling)) {
					if (b.nodeType == 1 && ++c > a) {
						return false;
					}
				}
				return (c == a);
			},
			even : function(b, a) {
				return Selectors.Pseudo["nth-child"].call(this, "2n+1", a);
			},
			odd : function(b, a) {
				return Selectors.Pseudo["nth-child"].call(this, "2n", a);
			},
			selected : function() {
				return this.selected;
			},
			enabled : function() {
				return (this.disabled === false);
			}
		});
var Swiff = new Class({
	Implements : [Options],
	options : {
		id : null,
		height : 1,
		width : 1,
		container : null,
		properties : {},
		params : {
			quality : "high",
			allowScriptAccess : "always",
			wMode : "transparent",
			swLiveConnect : true
		},
		callBacks : {},
		vars : {}
	},
	toElement : function() {
		return this.object;
	},
	initialize : function(m, n) {
		this.instance = "Swiff_" + $time();
		this.setOptions(n);
		n = this.options;
		var b = this.id = n.id || this.instance;
		var a = document.id(n.container);
		Swiff.CallBacks[this.instance] = {};
		var f = n.params, h = n.vars, g = n.callBacks;
		var i = $extend({
					height : n.height,
					width : n.width
				}, n.properties);
		var l = this;
		for (var d in g) {
			Swiff.CallBacks[this.instance][d] = (function(o) {
				return function() {
					return o.apply(l.object, arguments);
				};
			})(g[d]);
			h[d] = "Swiff.CallBacks." + this.instance + "." + d;
		}
		f.flashVars = Hash.toQueryString(h);
		if (Browser.Engine.trident) {
			i.classid = "clsid:D27CDB6E-AE6D-11cf-96B8-444553540000";
			f.movie = m;
		} else {
			i.type = "application/x-shockwave-flash";
			i.data = m;
		}
		var k = '<object id="' + b + '"';
		for (var j in i) {
			k += " " + j + '="' + i[j] + '"';
		}
		k += ">";
		for (var c in f) {
			if (f[c]) {
				k += '<param name="' + c + '" value="' + f[c] + '" />';
			}
		}
		k += "</object>";
		this.object = ((a) ? a.empty() : new Element("div")).set("html", k).firstChild;
	},
	replaces : function(a) {
		a = document.id(a, true);
		a.parentNode.replaceChild(this.toElement(), a);
		return this;
	},
	inject : function(a) {
		document.id(a, true).appendChild(this.toElement());
		return this;
	},
	remote : function() {
		return Swiff.remote.apply(Swiff, [this.toElement()].extend(arguments));
	}
});
Swiff.CallBacks = {};
Swiff.remote = function(obj, fn) {
	var rs = obj.CallFunction('<invoke name="' + fn
			+ '" returntype="javascript">'
			+ __flash__argumentsToXML(arguments, 2) + "</invoke>");
	return eval(rs);
};
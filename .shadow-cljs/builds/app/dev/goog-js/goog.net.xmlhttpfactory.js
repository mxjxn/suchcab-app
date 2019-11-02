["^ ","~:resource-id",["~:shadow.build.classpath/resource","goog/net/xmlhttpfactory.js"],"~:js","goog.provide(\"goog.net.XmlHttpFactory\");\n/**\n * @suppress {extraRequire}\n */\ngoog.require(\"goog.net.XhrLike\");\n/** @constructor */ goog.net.XmlHttpFactory = function() {\n};\n/** @private @type {?Object} */ goog.net.XmlHttpFactory.prototype.cachedOptions_ = null;\n/**\n * @return {!goog.net.XhrLike.OrNative}\n */\ngoog.net.XmlHttpFactory.prototype.createInstance = goog.abstractMethod;\n/**\n * @return {Object}\n */\ngoog.net.XmlHttpFactory.prototype.getOptions = function() {\n  return this.cachedOptions_ || (this.cachedOptions_ = this.internalGetOptions());\n};\n/**\n * @protected\n * @return {Object}\n */\ngoog.net.XmlHttpFactory.prototype.internalGetOptions = goog.abstractMethod;\n","~:source","// Copyright 2010 The Closure Library Authors. All Rights Reserved.\n//\n// Licensed under the Apache License, Version 2.0 (the \"License\");\n// you may not use this file except in compliance with the License.\n// You may obtain a copy of the License at\n//\n//      http://www.apache.org/licenses/LICENSE-2.0\n//\n// Unless required by applicable law or agreed to in writing, software\n// distributed under the License is distributed on an \"AS-IS\" BASIS,\n// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n// See the License for the specific language governing permissions and\n// limitations under the License.\n\n/**\n * @fileoverview Interface for a factory for creating XMLHttpRequest objects\n * and metadata about them.\n * @author dbk@google.com (David Barrett-Kahn)\n */\n\ngoog.provide('goog.net.XmlHttpFactory');\n\n/** @suppress {extraRequire} Typedef. */\ngoog.require('goog.net.XhrLike');\n\n\n\n/**\n * Abstract base class for an XmlHttpRequest factory.\n * @constructor\n */\ngoog.net.XmlHttpFactory = function() {};\n\n\n/**\n * Cache of options - we only actually call internalGetOptions once.\n * @type {?Object}\n * @private\n */\ngoog.net.XmlHttpFactory.prototype.cachedOptions_ = null;\n\n\n/**\n * @return {!goog.net.XhrLike.OrNative} A new XhrLike instance.\n */\ngoog.net.XmlHttpFactory.prototype.createInstance = goog.abstractMethod;\n\n\n/**\n * @return {Object} Options describing how xhr objects obtained from this\n *     factory should be used.\n */\ngoog.net.XmlHttpFactory.prototype.getOptions = function() {\n  return this.cachedOptions_ ||\n      (this.cachedOptions_ = this.internalGetOptions());\n};\n\n\n/**\n * Override this method in subclasses to preserve the caching offered by\n * getOptions().\n * @return {Object} Options describing how xhr objects obtained from this\n *     factory should be used.\n * @protected\n */\ngoog.net.XmlHttpFactory.prototype.internalGetOptions = goog.abstractMethod;\n","~:compiled-at",1572623028964,"~:source-map-json","{\n\"version\":3,\n\"file\":\"goog.net.xmlhttpfactory.js\",\n\"lineCount\":24,\n\"mappings\":\"AAoBAA,IAAAC,QAAA,CAAa,yBAAb,CAAA;AAGA;;;AAAAD,IAAAE,QAAA,CAAa,kBAAb,CAAA;AAQA,oBAAAF,IAAAG,IAAAC,eAAA,GAA0BC,QAAQ,EAAG;CAArC;AAQA,gCAAAL,IAAAG,IAAAC,eAAAE,UAAAC,eAAA,GAAmD,IAAnD;AAMA;;;AAAAP,IAAAG,IAAAC,eAAAE,UAAAE,eAAA,GAAmDR,IAAAS,eAAnD;AAOA;;;AAAAT,IAAAG,IAAAC,eAAAE,UAAAI,WAAA,GAA+CC,QAAQ,EAAG;AACxD,SAAO,IAAAJ,eAAP,KACK,IAAAA,eADL,GAC2B,IAAAK,mBAAA,EAD3B;AADwD,CAA1D;AAaA;;;;AAAAZ,IAAAG,IAAAC,eAAAE,UAAAM,mBAAA,GAAuDZ,IAAAS,eAAvD;;\",\n\"sources\":[\"goog/net/xmlhttpfactory.js\"],\n\"sourcesContent\":[\"// Copyright 2010 The Closure Library Authors. All Rights Reserved.\\n//\\n// Licensed under the Apache License, Version 2.0 (the \\\"License\\\");\\n// you may not use this file except in compliance with the License.\\n// You may obtain a copy of the License at\\n//\\n//      http://www.apache.org/licenses/LICENSE-2.0\\n//\\n// Unless required by applicable law or agreed to in writing, software\\n// distributed under the License is distributed on an \\\"AS-IS\\\" BASIS,\\n// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\\n// See the License for the specific language governing permissions and\\n// limitations under the License.\\n\\n/**\\n * @fileoverview Interface for a factory for creating XMLHttpRequest objects\\n * and metadata about them.\\n * @author dbk@google.com (David Barrett-Kahn)\\n */\\n\\ngoog.provide('goog.net.XmlHttpFactory');\\n\\n/** @suppress {extraRequire} Typedef. */\\ngoog.require('goog.net.XhrLike');\\n\\n\\n\\n/**\\n * Abstract base class for an XmlHttpRequest factory.\\n * @constructor\\n */\\ngoog.net.XmlHttpFactory = function() {};\\n\\n\\n/**\\n * Cache of options - we only actually call internalGetOptions once.\\n * @type {?Object}\\n * @private\\n */\\ngoog.net.XmlHttpFactory.prototype.cachedOptions_ = null;\\n\\n\\n/**\\n * @return {!goog.net.XhrLike.OrNative} A new XhrLike instance.\\n */\\ngoog.net.XmlHttpFactory.prototype.createInstance = goog.abstractMethod;\\n\\n\\n/**\\n * @return {Object} Options describing how xhr objects obtained from this\\n *     factory should be used.\\n */\\ngoog.net.XmlHttpFactory.prototype.getOptions = function() {\\n  return this.cachedOptions_ ||\\n      (this.cachedOptions_ = this.internalGetOptions());\\n};\\n\\n\\n/**\\n * Override this method in subclasses to preserve the caching offered by\\n * getOptions().\\n * @return {Object} Options describing how xhr objects obtained from this\\n *     factory should be used.\\n * @protected\\n */\\ngoog.net.XmlHttpFactory.prototype.internalGetOptions = goog.abstractMethod;\\n\"],\n\"names\":[\"goog\",\"provide\",\"require\",\"net\",\"XmlHttpFactory\",\"goog.net.XmlHttpFactory\",\"prototype\",\"cachedOptions_\",\"createInstance\",\"abstractMethod\",\"getOptions\",\"goog.net.XmlHttpFactory.prototype.getOptions\",\"internalGetOptions\"]\n}\n"]
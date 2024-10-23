import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.LibraryProductFlavor
import com.android.build.api.dsl.ProductFlavor
import org.gradle.api.NamedDomainObjectContainer

interface BuildProductFlavor {
    val name: String

    fun appCreate(namedDomain: NamedDomainObjectContainer<ApplicationProductFlavor>): ProductFlavor

    fun libCreate(namedDomain: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor
}

object ProductFlavorDevelop : BuildProductFlavor {
    override val name = "dev"

    override fun appCreate(namedDomain: NamedDomainObjectContainer<ApplicationProductFlavor>): ProductFlavor {
        return namedDomain.create(name) {
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            dimension = BuildProductDimensions.ENVIRONMENT
        }
    }

    override fun libCreate(namedDomain: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor {
        return namedDomain.create(name) {
            dimension = BuildProductDimensions.ENVIRONMENT
        }
    }
}

object ProductFlavorStaging : BuildProductFlavor {
    override val name = "staging"

    override fun appCreate(namedDomain: NamedDomainObjectContainer<ApplicationProductFlavor>): ProductFlavor {
        return namedDomain.create(name) {
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-staging"
            dimension = BuildProductDimensions.ENVIRONMENT
        }
    }

    override fun libCreate(namedDomain: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor {
        return namedDomain.create(name) {
            dimension = BuildProductDimensions.ENVIRONMENT
        }
    }
}

object ProductFlavorProduction : BuildProductFlavor {
    override val name = "prod"

    override fun appCreate(namedDomain: NamedDomainObjectContainer<ApplicationProductFlavor>): ProductFlavor {
        return namedDomain.create(name) {
            dimension = BuildProductDimensions.ENVIRONMENT
        }
    }

    override fun libCreate(namedDomain: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor {
        return namedDomain.create(name) {
            dimension = BuildProductDimensions.ENVIRONMENT
        }
    }
}
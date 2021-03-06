package org.jfrog.artifactory.client.model.builder.impl

import org.jfrog.artifactory.client.model.ChecksumPolicyType
import org.jfrog.artifactory.client.model.RepositoryType
import org.jfrog.artifactory.client.model.impl.ChecksumPolicyTypeImpl
import org.jfrog.artifactory.client.model.LocalRepository
import org.jfrog.artifactory.client.model.Repository
import org.jfrog.artifactory.client.model.builder.LocalRepositoryBuilder
import org.jfrog.artifactory.client.model.impl.LocalRepositoryImpl
import org.jfrog.artifactory.client.model.impl.RepositoryTypeImpl

import static org.jfrog.artifactory.client.model.PackageType.*
import static ChecksumPolicyTypeImpl.client_checksums
/**
 *
 * @author jbaruch
 * @since 31/07/12
 */
class LocalRepositoryBuilderImpl extends NonVirtualRepositoryBuilderBase<LocalRepositoryBuilder, LocalRepository> implements LocalRepositoryBuilder {

    private LocalRepositoryBuilderImpl() {
        super([maven, gradle, ivy, sbt, nuget, gems, npm, bower, debian, pypi, docker, vagrant, gitlfs, yum, generic])
        this.repoLayoutRef = Repository.MAVEN_2_REPO_LAYOUT
        this.checksumPolicyType = client_checksums
    }

    private ChecksumPolicyType checksumPolicyType
    private boolean calculateYumMetadata
    private int yumRootDepth

    LocalRepositoryBuilder checksumPolicyType(ChecksumPolicyType checksumPolicyType) {
        this.checksumPolicyType = checksumPolicyType
        this
    }

    LocalRepositoryBuilder calculateYumMetadata(boolean calculateYumMetadata) {
        this.calculateYumMetadata = calculateYumMetadata
        this
    }

    LocalRepositoryBuilder yumRootDepth(int yumRootDepth) {
        this.yumRootDepth = yumRootDepth
        this
    }

    LocalRepository build() {
        validate()
        return new LocalRepositoryImpl(description, excludesPattern, includesPattern, key, notes, blackedOut,
            handleReleases, handleSnapshots, maxUniqueSnapshots, propertySets, snapshotVersionBehavior,
            suppressPomConsistencyChecks, checksumPolicyType, repoLayoutRef, packageType, enableNuGetSupport,
            archiveBrowsingEnabled, calculateYumMetadata, yumRootDepth, enableGemsSupport, enableNpmSupport,
            enableVagrantSupport, enableBowerSupport, enableGitLfsSupport, enableDebianSupport,
            enableDockerSupport, enablePypiSupport, debianTrivialLayout)
    }

    @Override
    RepositoryType getRepositoryType() {
        return RepositoryTypeImpl.LOCAL
    }

}

<?xml version="1.0" encoding="ISO-8859-1"?>
<project name="PROJECT_NAME" basedir="." default="all">
    <property environment="env"/>
	
    <property name="src.dir" value="${basedir}/src"/>
    <property name="bin.dir" value="${basedir}/bin"/>
    <property name="lib.dir" value="${basedir}/lib"/>
    <property name="dist.dir" value="${basedir}/dist"/>
    <property name="conf.dir" value="${basedir}/conf"/>
    <property name="class.dir" value="${lib.dir}/classes"/>
	<property name="tomcat.dir" value="${env.CATALINA_HOME}"/>

	<property name="template.dir" value="${basedir}/view"/>
    <property name="pkg.prefix" value="PACKAGE_PREFIX"/>

	<condition property="debug.arg" value="-debug" else="">
		<or>
		  <isset property="line.numbers"/>
		  <isset property="debug"/>
		</or>
	</condition>

	<condition property="regen.arg" value="-force" else="">
		<or>
		  <isset property="regen"/>
		  <isset property="debug"/>
		</or>
	</condition>

	<path id="project.classpath">
		<pathelement path="${class.dir}" />
		<fileset dir="${lib.dir}">
			<include name="*.jar"/>
		</fileset>
		<pathelement path="${tomcat.dir}/lib/servlet-api.jar"/>
	</path>

	<target name="init">
		<mkdir dir="${class.dir}" />
		<mkdir dir="${dist.dir}/WEB-INF/lib" />
	</target>

	<target name="generate" depends="init">
		<java classname="com.pmdesigns.jvc.tools.JVCGenerator">
		<classpath path="${lib.dir}/jvc.jar"/>
		<arg line="${template.dir} ${src.dir} ${pkg.prefix} ${regen.arg} ${debug.arg}" />
		</java>
	</target>
	
	<target name="compile" depends="generate">
		<javac
		debug="true"
		srcdir="${src.dir}"
		destdir="${class.dir}"
		classpathref="project.classpath"
		/>
		
		<copy file="${conf.dir}/log4j.properties" todir="${class.dir}" overwrite="true"/>
	</target>	
	
	<target name="test" depends="compile">
		<java classname="test.JVCTests" classpathref="project.classpath">
		<arg line="${pkg.prefix}" />
		</java>
	</target>
	
	<target name="install" depends="compile">
		<copy todir="${dist.dir}/WEB-INF/lib" overwrite="true">
		<fileset dir="${lib.dir}" includes="*.jar" excludes="junit*.jar" />
		</copy>

		<copy todir="${dist.dir}/WEB-INF/classes" overwrite="true">
		<fileset dir="${class.dir}" />
		</copy>

		<copy todir="${dist.dir}" overwrite="true">
		<fileset dir="${basedir}/view">
		  <exclude name="**/*.jhtml"/>
		  <exclude name="*~"/>
		  <exclude name="tests/**"/>
		  <exclude name="examples/**"/>
		</fileset>
		</copy>
		
		<delete dir="${tomcat.dir}/webapps/PROJECT_NAME" />
  
		<war
		destfile="${dist.dir}/PROJECT_NAME.war"
		basedir="${dist.dir}"
		excludes="PROJECT_NAME.war"
		/>

		<copy
		file="${dist.dir}/PROJECT_NAME.war"
		todir="${tomcat.dir}/webapps"
		overwrite="true"
		/>
	</target>

	<!-- convenience target for developers -->
	<!-- stop tomcat so we can delete the old expanded war file (part of the install target) -->
	<!-- install the project and then restart tomcat -->
    <target name="rev">
		<exec executable="${tomcat.dir}/bin/shutdown.sh">
		    <arg value="-force"/>
		</exec>

	    <antcall target="install" />
  
		<exec executable="${tomcat.dir}/bin/startup.sh"/>
	</target>

	<target name="clean">
	    <delete dir="${class.dir}"/>
	</target>
	
	<target name="realclean" depends="clean">
	    <defaultexcludes remove="**/*~"/>
	    <delete	file="${dist.dir}/PROJECT_NAME.war"/>
	    <delete	dir="${dist.dir}/WEB-INF/classes"/>
	    <delete	dir="${dist.dir}/WEB-INF/lib"/>
	    <delete>
			<fileset dir="${basedir}" includes="**/*~"/>
			<fileset dir="${src.dir}" includes="**/*.java">
				<contains text="This is a machine generated file. DO NOT EDIT." casesensitive="yes"/>
			</fileset>
		</delete>
	</target>
	
	<target name="all" depends="compile">
	</target>
		
</project>


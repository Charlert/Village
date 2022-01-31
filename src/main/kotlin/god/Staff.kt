package charlert.village.god

import charlert.village.action.GodAction
import charlert.village.action.GodAction.create
import charlert.village.action.GodAction.draw
import charlert.village.action.VillagerAction
import charlert.village.action.VillagerAction.*
import charlert.village.creature.Creature
import charlert.village.creature.event.Chase
import charlert.village.creature.event.Event
import charlert.village.creature.factory.BatFactory
import charlert.village.creature.factory.VillagerFactory
import charlert.village.world.World
import net.sourceforge.argparse4j.ArgumentParsers

class Staff {
    val world = World(16, 16)
    private val villagerFactory = VillagerFactory(this)
    private val batFactory = BatFactory(this)

    private fun run(action: GodAction) {
        when (action) {
            create -> {
                val villager = villagerFactory.create(world.getLastId() + 1)
                world.add(villager)
                println(villager.info())
            }
            GodAction.show -> print(world.getInformation())
            draw -> world.draw()
        }
    }

    private fun run(action: VillagerAction, id: Int, targetId: Int = -1) {
        val villager = world.getCreature(id)
        val target = world.getCreature(targetId)
        if (villager == null) {
            println("error: $id not found")
            return
        }
        when (action) {
            hello -> villager.hello()
            die -> villager.die("god")
            show -> villager.announcer.send(villager.info())
            kill -> {
                if (target == null) {
                    println("error: $targetId not found")
                    return
                }
                villager.kill(target)
            }
            walkto -> {
                if (target == null) {
                    println("error: $targetId not found")
                    return
                }
                villager.walkTo(target.location)
            }
        }
    }

    fun let(args: Array<String>) {
        val letParser = ArgumentParsers.newFor("let").build()
        letParser.addArgument("villager").type(Int::class.java).help("villager id").required(true)
        letParser.addArgument("action").type(String::class.java).help("action").required(true)
            .choices(VillagerAction.values().map { it.name })
        letParser.addArgument("target").type(Int::class.java).help("target id").required(false).nargs("?")
        letParser.addArgument("-t").type(Int::class.java).help("times").required(false).nargs("?").dest("times")

        val letArgs = letParser.parseArgs(args)
        val villagerId = letArgs.getInt("villager")
        val action = VillagerAction.valueOf(letArgs.getString("action"))
        val targetId = letArgs.getInt("target") ?: -1
        val times = letArgs.getInt("times") ?: 1
        for (i in 0 until times) {
            run(action, villagerId, targetId)
        }
    }

    fun plz(args: Array<String>) {
        val plzParser = ArgumentParsers.newFor("plz").build()
        plzParser.addArgument("action").type(String::class.java).help("action").required(true)
            .choices(GodAction.values().map { it.name })
        plzParser.addArgument("times").type(Int::class.java).help("times").required(false).nargs("?")


        val plzArgs = plzParser.parseArgs(args)
        val action = GodAction.valueOf(plzArgs.getString("action"))
        val times = plzArgs.getInt("times") ?: 1
        for (i in 0 until times) {
            run(action)
        }
    }

    fun summon(args: Array<String>) {
        val summonParser = ArgumentParsers.newFor("summon").build()
        summonParser.addArgument("type").type(String::class.java).help("type").required(true)
        summonParser.addArgument("x").type(Int::class.java).help("x").required(true)
        summonParser.addArgument("y").type(Int::class.java).help("y").required(true)
        summonParser.addArgument("name").type(String::class.java).help("name").required(false).nargs("?")

        val summonArgs = summonParser.parseArgs(args)
        val type = summonArgs.getString("type")
        val x = summonArgs.getInt("x")
        val y = summonArgs.getInt("y")
        val name = summonArgs.getString("name")
        when (type) {
            "villager" -> {
                val villager = villagerFactory.create(world.getLastId() + 1)
                villager.location.x = x
                villager.location.y = y
                villager.name = name ?: villager.name
                world.add(villager)
            }
            "bat" -> {
                val bat = batFactory.create(world.getLastId() + 1)
                bat.location.x = x
                bat.location.y = y
                bat.name = name ?: bat.name
                world.add(bat)
            }
            else -> {
                println("error: unknown type $type")
            }
        }
    }

    fun remove(event: Event) {
        event.owner.events.remove(event)
    }

    fun remove(creature: Creature) {
        world.remove(creature)
    }

    fun test() {
        world.getCreature(1)?.let {
            it.events.add(Chase(this, it, world.getCreature(2)!!))
        }
    }

    fun go() {
        for (creature in world.getCreatures()) {
            for (event in creature.events) {
                event.execute()
            }
        }
    }
}
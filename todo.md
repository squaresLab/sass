# TODO
The biggest things are:

+ getting tactics to not take affect until their duration
To do this, I can add behavior to where events are processed
i.e., when a start event happens, instead of having the system state
accept it, just put a complete event into the timeline, which will trigger
the actual change or not

I must also update how the probabilities are handled to be compatible with this,
i.e., avoid double counting them (first event always gets 1.0)

+ continuing along these lines, I need to fix handing probabilities for tactics
that fail for reasons other than simulation (intentional fail)

+ building back in support for try-catch and for loop tactics

This can be done by having those start events put the other events on the list

+ Then I can put the parallel tactics back in
This will work like try-catch, simply putting the needed events on the timeline

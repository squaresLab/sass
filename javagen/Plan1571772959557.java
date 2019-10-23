public class Plan1571772959557 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("C");
}

if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

} else {
StartServer("A");
}

if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
DecreaseDimmer("B");
}


if ( StartServer("B") ) {
StartServer("B");
} else {
StartServer("B");
}

for (int i = 0; i < 3 ; i++) {
StartServer("C");
}


if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
DecreaseDimmer("C");
}





}
}

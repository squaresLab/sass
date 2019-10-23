public class Plan1571775007017 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

} else {
DecreaseDimmer("A");
}

for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("B");
}


if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
}


}


}
}
